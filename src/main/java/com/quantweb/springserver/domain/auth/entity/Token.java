package com.quantweb.springserver.domain.auth.entity;

import com.quantweb.springserver.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {

  @Id
  @Column(name = "token_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false)
  private String refreshToken;

  public Token(User user, String refreshToken) {
    this.user = user;
    this.refreshToken = refreshToken;
  }

  public boolean isDifferentRefreshToken(String refreshToken) {
    return !this.refreshToken.equals(refreshToken);
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
