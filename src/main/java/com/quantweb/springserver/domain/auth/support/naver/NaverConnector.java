package com.quantweb.springserver.domain.auth.support.naver;

import com.quantweb.springserver.domain.auth.support.naver.dto.NaverTokenResponse;
import com.quantweb.springserver.domain.auth.support.naver.dto.NaverUserResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Getter
@RequiredArgsConstructor
@Component
public class NaverConnector {

  private static final String BEARER_TYPE = "Bearer";
  private static final RestTemplate REST_TEMPLATE = new RestTemplate();

  private final NaverProvider provider;

  public ResponseEntity<NaverUserResponse> requestNaverUserInfo(String code, String redirectUrl) {
    String naverToken = requestAccessToken(code, redirectUrl);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", String.join(" ", BEARER_TYPE, naverToken));
    HttpEntity<Void> entity = new HttpEntity<>(headers);

    return REST_TEMPLATE.exchange(
        provider.getUserInfoUrl(), HttpMethod.GET, entity, NaverUserResponse.class);
  }

  private String requestAccessToken(String code, String redirectUrl) {
    //        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(
    //            createBody(code, redirectUrl), createHeaders(code,redirectUrl));

    String url = getAceessTokenUrl(code, provider.getAccessTokenUrl());
    ResponseEntity<NaverTokenResponse> response =
        REST_TEMPLATE.getForEntity(url, NaverTokenResponse.class);

    return extractAccessToken(response);
  }

  private String getAceessTokenUrl(String code, String url) {
    Map<String, String> params = new HashMap<>();
    params.put("grant_type", provider.getGrantType());
    params.put("client_id", provider.getClientId());
    params.put("client_secret", provider.getClientSecret());
    params.put("code", code);
    params.put("state", provider.getState());

    return url + "?" + concatParams(params);
  }

  private String concatParams(Map<String, String> params) {
    return params.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining("&"));
  }

  private HttpHeaders createHeaders(String code, String redirectUrl) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }

  private MultiValueMap<String, String> createBody(String code, String redirectUrl) {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("code", code);
    body.add("client_id", provider.getClientId());
    body.add("client_secret", provider.getClientSecret());
    body.add("redirect_uri", redirectUrl);
    body.add("grant_type", provider.getGrantType());
    body.add("state", provider.getState());
    return body;
  }

  private String extractAccessToken(ResponseEntity<NaverTokenResponse> responseEntity) {
    NaverTokenResponse response =
        Optional.ofNullable(responseEntity.getBody())
            .orElseThrow(() -> new RuntimeException("네이버 토큰을 가져오는데 실패했습니다."));

    return response.getAccessToken();
  }
}
