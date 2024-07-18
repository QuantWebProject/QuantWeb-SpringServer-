package com.quantweb.springserver.user.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u  join fetch u.oauthList o " +
      "WHERE o.uid = :oauthId")
  Optional<User> findByOauthId(String oauthId);

  Optional<User> findById(Long Id);
}
