package com.quantweb.springserver.auth.service.dto.response;

import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LoginResponse {

  private Cookie accessToken;
  private Cookie refreshToken;
}
