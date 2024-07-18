package com.quantweb.springserver.user.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
}
