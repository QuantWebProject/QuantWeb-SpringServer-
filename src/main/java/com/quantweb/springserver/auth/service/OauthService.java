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
import com.quantweb.springserver.user.entity.User;
import com.quantweb.springserver.user.entity.UserRepository;
import com.quantweb.springserver.user.user_status.UserStatus;
import com.quantweb.springserver.user_image.entity.UserImage;
import com.quantweb.springserver.user_image.service.UserImageService;
import jakarta.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OauthService {

  private final TokenService tokenService;
  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final KakaoConnector kakaoConnector;
  private final KakaoProvider kakaoProvider;
  private final GoogleConnector googleConnector;
  private final GoogleProvider googleProvider;
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
      KakaoUserResponse response = kakaoConnector.requestKakaoUserInfo(code, redirectUrl).getBody();
      String nickname = response.getKakaoAccount().getProfile().getNickName();
      return userRepository.findByNickname(nickname)
          .orElseGet(() -> saveKakaoUser(response));
    }
    if (Objects.equals(type, "google")) {
      GoogleUserResponse response = googleConnector.requestGoogleUserInfo(code, redirectUrl)
          .getBody();
      String email = response.getEmailAddresses().get(0).getValue();
      return userRepository.findByEmail(email)
          .orElseGet(() -> saveGoogleUser(response));
    } else {
      throw new RuntimeException("지원하지 않는 oauth 타입입니다.");
    }
  }

  private User saveGoogleUser(GoogleUserResponse response) {
    String imageUrl = getGoogleUserImage(response);
    UserImage savedUserImage = userImageService.saveUserImage(imageUrl);

    User user = User.builder()
        .userStatus(UserStatus.ACTIVATE)
        .oauthId(response.getResourceName())
        .provider("google")
        .isAdmin(false)
        .userImage(savedUserImage)
        .nickname(RandomNickName.generateRandomNickname())
        .name(response.getNames().get(0).getDisplayName())
        .age(response.getBirthdays().get(0).getDate().getYear())
        .sex(response.getGenders().get(0).getValue())
        .email(response.getEmailAddresses().get(0).getValue())
        .createdAt(LocalDateTime.now())
        .build();

    return userRepository.save(user);
  }

  private String getGoogleUserImage(GoogleUserResponse response) {
    String gender = response.getGenders().get(0).getValue();
    if (Objects.equals(gender, "male")) {
      return "male";
    }
    if (Objects.equals(gender, "female")) {
      return "female";
    }
    return null;
  }

  private User saveKakaoUser(KakaoUserResponse response) {
    String imageUrl = getKakaoUserImage(response);
    UserImage savedUserImage = userImageService.saveUserImage(imageUrl);

    User user = User.builder()
        .userStatus(UserStatus.ACTIVATE)
        .oauthId(String.valueOf(response.getId()))
        .provider("kakao")
        .isAdmin(false)
        .userImage(savedUserImage)
        .name(response.getKakaoAccount().getName())
        .nickname(response.getKakaoAccount().getProfile().getNickName())
        .age(Integer.valueOf(response.getKakaoAccount().getBirthYear()))
        .sex(response.getKakaoAccount().getGender())
        .email(response.getKakaoAccount().getEmail())
        .createdAt(LocalDateTime.now())
        .build();
    return userRepository.save(user);
  }

  private String getKakaoUserImage(KakaoUserResponse response) {
    String gender = response.getKakaoAccount().getGender();
    if (Objects.equals(gender, "male")) {
      return "male";
    }
    if (Objects.equals(gender, "female")) {
      return "female";
    }
    return null;
  }

  private String getAuthLink(String type, String redirectUrl) {
    return switch (type) {
      case "kakao" -> kakaoProvider.generateAuthUrl(redirectUrl);
      case "google" -> googleProvider.generateAuthUrl(redirectUrl);
      default -> throw new RuntimeException("지원하지 않는 oauth 타입입니다.");
    };
  }

  public AccessTokenResponse reissueAccessToken(Long userId) {
    boolean isExistMember = userRepository.existsById(userId);
    if (!isExistMember) {
      throw new RuntimeException("유효하지 않은 토큰입니다.");
    }
    String accessToken = jwtTokenProvider.createAccessToken(userId);
    Cookie accessCookie = createCookie("access_token", accessToken, 60 * 60 * 24);

    return new AccessTokenResponse(accessCookie);
  }

  @Transactional
  public void logout(Long memberId) {
    tokenService.deleteByMemberId(memberId);
  }
}
