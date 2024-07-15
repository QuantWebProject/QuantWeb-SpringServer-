package com.quantweb.springserver.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import com.quantweb.springserver.auth.entity.Token;
import com.quantweb.springserver.auth.entity.TokenRepository;
import com.quantweb.springserver.auth.support.CookieExtractor;
import com.quantweb.springserver.auth.support.JwtTokenProvider;

@RequiredArgsConstructor
public class RefreshTokenAuthInterceptor implements HandlerInterceptor {

  private final JwtTokenProvider jwtTokenProvider;
  private final TokenRepository tokenRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String refreshToken = CookieExtractor.findToken("refresh_token", request.getCookies());

    if (jwtTokenProvider.validateTokenNotUsable(refreshToken)) {
      throw new RuntimeException("토큰이 유효하지 않습니다.");
    }

    Long userId = jwtTokenProvider.getPayload(refreshToken);
    Token token = tokenRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("토큰이 존재하지 않습니다."));

    if (token.isDifferentRefreshToken(refreshToken)) {
      throw new RuntimeException("토큰이 일치하지 않습니다.");
    }
    return true;
  }

}
