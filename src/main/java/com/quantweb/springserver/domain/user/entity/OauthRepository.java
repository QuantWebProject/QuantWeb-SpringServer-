package com.quantweb.springserver.domain.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<Oauth, Long> {}
