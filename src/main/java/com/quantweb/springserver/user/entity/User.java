package com.quantweb.springserver.user.entity;

import com.quantweb.springserver.user.user_status.UserStatus;
import com.quantweb.springserver.user.user_status.UserStatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_status_id")
  @Convert(converter = UserStatusConverter.class)
  private UserStatus userStatus;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Oauth> oauthList = new ArrayList<>();

  @Column(name = "user_nickname")
  private String nickname;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_email")
  private String email;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Column(name = "user_latest_login_at")
  private LocalDateTime latestLoginAt;

  @Column(name = "user_created_at")
  private LocalDateTime createdAt;

  @Column(name = "user_deleted_at")
  private LocalDateTime deletedAt;

  public void updateLatestLoginAt(LocalDateTime now) {
    this.latestLoginAt = now;
  }
}
