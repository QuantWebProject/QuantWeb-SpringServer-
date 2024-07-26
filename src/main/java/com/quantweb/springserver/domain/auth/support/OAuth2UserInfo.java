package com.quantweb.springserver.domain.auth.support;

public interface OAuth2UserInfo {

    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();
}
