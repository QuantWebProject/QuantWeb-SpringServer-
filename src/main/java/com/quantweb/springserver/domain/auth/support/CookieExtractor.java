package com.quantweb.springserver.domain.auth.support;

import jakarta.servlet.http.Cookie;

public class CookieExtractor {

  public static String findToken(String token, Cookie[] cookies) {
    String value = null;
    for (Cookie cookie : cookies) {
      if (token.equals(cookie.getName())) {
        value = cookie.getValue();
        break;
      }
    }
    return value;
  }
}
