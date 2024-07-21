package com.quantweb.springserver.auth.service;

import com.quantweb.springserver.auth.service.dto.response.AccessTokenResponse;
import com.quantweb.springserver.auth.service.dto.response.LoginResponse;
import com.quantweb.springserver.auth.service.dto.response.OauthLinkResponse;
import com.quantweb.springserver.auth.support.JwtTokenProvider;
import com.quantweb.springserver.auth.support.RandomNickName;
import com.quantweb.springserver.auth.support.google.GoogleConnector;
import com.quantweb.springserver.auth.support.google.GoogleProvider;
import com.quantweb.springserver.auth.support.google.dto.GoogleUserResponse;
import com.quantweb.springserver.auth.support.kakao.KakaoConnector;
import com.quantweb.springserver.auth.support.kakao.KakaoProvider;
import com.quantweb.springserver.auth.support.kakao.dto.KakaoUserResponse;
import com.quantweb.springserver.auth.support.naver.NaverConnector;
import com.quantweb.springserver.auth.support.naver.NaverProvider;
import com.quantweb.springserver.auth.support.naver.dto.NaverUserResponse;
import com.quantweb.springserver.user.entity.Oauth;
import com.quantweb.springserver.user.entity.OauthRepository;
import com.quantweb.springserver.user.entity.User;
import com.quantweb.springserver.user.entity.UserRepository;
import com.quantweb.springserver.user.user_status.UserStatus;
import jakarta.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OauthService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final OauthRepository oauthRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoConnector kakaoConnector;
    private final KakaoProvider kakaoProvider;
    private final GoogleConnector googleConnector;
    private final GoogleProvider googleProvider;
    private final NaverProvider naverProvider;
    private final NaverConnector naverConnector;
    private final int ACCESS_COOKIE_AGE = (int) TimeUnit.MINUTES.toSeconds(15);
    private final int REFRESH_COOKIE_AGE = (int) TimeUnit.DAYS.toSeconds(30);


    public OauthLinkResponse generateAuthUrl(String type, String redirectUrl) {
        String oauthLink = getAuthLink(type, redirectUrl);
        return new OauthLinkResponse(oauthLink);
    }

    @Transactional
    public LoginResponse requestAccessToken(String type, String code, String redirectUrl) {
        User user = checkAndSaveUser(type, code, redirectUrl);
        user.updateLatestLoginAt(LocalDateTime.now());

        String token = jwtTokenProvider.createAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        tokenService.synchronizeRefreshToken(user, refreshToken);

        Cookie accessCookie = createCookie("access_token", token, ACCESS_COOKIE_AGE);
        Cookie refreshCookie = createCookie("refresh_token", refreshToken, REFRESH_COOKIE_AGE);

        return new LoginResponse(accessCookie, refreshCookie);
    }

    private Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        return cookie;
    }

    private User checkAndSaveUser(String type, String code, String redirectUrl) {
        if (Objects.equals(type, "kakao")) {
            KakaoUserResponse response = kakaoConnector.requestKakaoUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = String.valueOf(response.getId());
            return userRepository.findByOauthId(oauthId)
                .orElseGet(() -> {
                    User user = saveKakaoUser(response);
                    saveOauth(oauthId, user, "kakao");
                    return user;
                });
        }
        if (Objects.equals(type, "google")) {
            GoogleUserResponse response = googleConnector.requestGoogleUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = response.getId().substring(0, 8);
            return userRepository.findByOauthId(oauthId)
                .orElseGet(() -> {
                    User user = saveGoogleUser(response);
                    saveOauth(oauthId, user, "google");
                    return user;
                });
        }
        if (Objects.equals(type, "naver")) {
            NaverUserResponse response = naverConnector.requestNaverUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = response.getNaverUserDetail().getId();
            return userRepository.findByOauthId(oauthId)
                .orElseGet(() -> {
                    User user = saveNaverUser(response);
                    saveOauth(oauthId, user, "naver");
                    return user;
                });
        } else {
            throw new RuntimeException("지원하지 않는 oauth 타입입니다.");
        }
    }

    private void saveOauth(String uid, User user, String provider) {
        Oauth oauth = new Oauth(uid, user, provider);
        oauthRepository.save(oauth);
    }

    private User saveGoogleUser(GoogleUserResponse response) {
        User user = User.builder()
            .userStatus(UserStatus.ACTIVATE)
            .isAdmin(false)
            .nickname(RandomNickName.generateRandomNickname())
            .name(response.getName())
            .email(response.getEmail())
            .createdAt(LocalDateTime.now())
            .build();

        return userRepository.save(user);
    }

    private User saveKakaoUser(KakaoUserResponse response) {
        User user = User.builder()
            .userStatus(UserStatus.ACTIVATE)
            .isAdmin(false)
            .name(response.getKakaoAccount().getName())
            .nickname(RandomNickName.generateRandomNickname())
            .email(response.getKakaoAccount().getEmail())
            .createdAt(LocalDateTime.now())
            .build();

        return userRepository.save(user);
    }

    private User saveNaverUser(NaverUserResponse response) {
        User user = User.builder()
            .userStatus(UserStatus.ACTIVATE)
            .isAdmin(false)
            .name(response.getNaverUserDetail().getName())
            .nickname(RandomNickName.generateRandomNickname())
            .email(response.getNaverUserDetail().getEmail())
            .createdAt(LocalDateTime.now())
            .build();

        return userRepository.save(user);
    }

    private String getAuthLink(String type, String redirectUrl) {
        return switch (type) {
            case "kakao" -> kakaoProvider.generateAuthUrl(redirectUrl);
            case "google" -> googleProvider.generateAuthUrl(redirectUrl);
            case "naver" -> naverProvider.generateAuthUrl(redirectUrl);
            default -> throw new RuntimeException("지원하지 않는 oauth 타입입니다.");
        };
    }

    public AccessTokenResponse reissueAccessToken(Long userId) {
        boolean isExistMember = userRepository.existsById(userId);
        if (!isExistMember) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
        String accessToken = jwtTokenProvider.createAccessToken(userId);
        Cookie accessCookie = createCookie("access_token", accessToken, ACCESS_COOKIE_AGE);

        return new AccessTokenResponse(accessCookie);
    }

    @Transactional
    public void logout(Long memberId) {
        tokenService.deleteByMemberId(memberId);
    }

    @Transactional
    public void syncLogin(Long userId, String type, String code, String redirectUrl) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        checkAndSaveOauth(user, type, code, redirectUrl);
    }

    private void checkAndSaveOauth(User user, String type, String code, String redirectUrl) {
        if (Objects.equals(type, "kakao")) {
            KakaoUserResponse response = kakaoConnector.requestKakaoUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = String.valueOf(response.getId());
            saveOauth(oauthId, user, "kakao");
        }
        if (Objects.equals(type, "google")) {
            GoogleUserResponse response = googleConnector.requestGoogleUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = String.valueOf(response.getId()).substring(0, 8);
            saveOauth(oauthId, user, "google");
        }
        if (Objects.equals(type, "naver")) {
            NaverUserResponse response = naverConnector.requestNaverUserInfo(code, redirectUrl)
                .getBody();
            String oauthId = response.getNaverUserDetail().getId();
            saveOauth(oauthId, user, "naver");
        }
    }

}
