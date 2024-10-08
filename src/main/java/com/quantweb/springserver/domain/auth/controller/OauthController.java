package com.quantweb.springserver.domain.auth.controller;

import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.auth.config.AuthenticationRefreshPrincipal;
import com.quantweb.springserver.domain.auth.service.OauthService;
import com.quantweb.springserver.domain.auth.service.dto.response.AccessTokenResponse;
import com.quantweb.springserver.domain.auth.service.dto.response.LoginResponse;
import com.quantweb.springserver.domain.auth.service.dto.response.OauthLinkResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth/oauth2")
@RestController
public class OauthController {

  private final OauthService oauthService;

  @GetMapping(
      value = "/login",
      params = {"type", "redirectUrl"})
  public ResponseEntity<OauthLinkResponse> access(
      @RequestParam String type, @RequestParam String redirectUrl) {
    OauthLinkResponse response = oauthService.generateAuthUrl(type, redirectUrl);
    return ResponseEntity.ok(response);
  }

  @GetMapping(
      value = "/login",
      params = {"type", "code", "redirectUrl"})
  public ResponseEntity<LoginResponse> login(
      HttpServletResponse response,
      @RequestParam String type,
      @RequestParam String code,
      @RequestParam String redirectUrl) {
    LoginResponse loginResponse = oauthService.requestAccessToken(type, code, redirectUrl);

    String accessCookieHeader = setCookieHeader(loginResponse.getAccessToken());
    String refreshCookieHeader = setCookieHeader(loginResponse.getRefreshToken());

    response.addHeader("Set-Cookie", accessCookieHeader);
    response.addHeader("Set-Cookie", refreshCookieHeader);

    return ResponseEntity.ok().build();
  }

  @Authenticated
  @PostMapping("/logout")
  public ResponseEntity<Void> logout(@AuthenticationPrincipal Long memberId) {
    oauthService.logout(memberId);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<Void> reissueAccessToken(
      HttpServletResponse response, @AuthenticationRefreshPrincipal Long memberId) {
    AccessTokenResponse accessToken = oauthService.reissueAccessToken(memberId);

    String accessCookieHeader = setCookieHeader(accessToken.getAccessToken());
    response.addHeader("Set-Cookie", accessCookieHeader);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/sync")
  public ResponseEntity<Void> syncLogin(
      @AuthenticationPrincipal Long memberId,
      @RequestParam String type,
      @RequestParam String code,
      @RequestParam String redirectUrl) {
    oauthService.syncLogin(memberId, type, code, redirectUrl);

    return ResponseEntity.ok().build();
  }

  private String setCookieHeader(Cookie cookie) {
    return String.format(
        "%s=%s; Path=%s; Max-Age=%d; Secure; HttpOnly; SameSite=None",
        cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getMaxAge());
  }
}
