package com.quantweb.springserver.auth.support.naver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverProvider {

  private final String clientId;
  private final String clientSecret;
  private final String authUrl;
  private final String accessTokenUrl;
  private final String userInfoUrl;
  private final String grantType;
  private final String state;

  public NaverProvider(
      @Value("${oauth2.naver.client.id}") String clientId,
      @Value("${oauth2.naver.client.secret}") String clientSecret,
      @Value("${oauth2.naver.url.auth}") String authUrl,
      @Value("${oauth2.naver.url.token}") String accessTokenUrl,
      @Value("${oauth2.naver.url.userinfo}") String userInfoUrl,
      @Value("${oauth2.naver.grant-type}") String grantType,
      @Value("${oauth2.naver.client.state}") String state) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.authUrl = authUrl;
    this.accessTokenUrl = accessTokenUrl;
    this.userInfoUrl = userInfoUrl;
    this.grantType = grantType;
    this.state = state;
  }

  public String generateAuthUrl(String redirectUrl) {
    Map<String, String> params = new HashMap<>();
    params.put("response_type", "code");
    params.put("client_id", clientId);
    params.put("redirect_uri", redirectUrl);
    params.put("state", state);
    return authUrl + "?" + concatParams(params);
  }

  private String concatParams(Map<String, String> params) {
    return params.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining("&"));
  }
}
