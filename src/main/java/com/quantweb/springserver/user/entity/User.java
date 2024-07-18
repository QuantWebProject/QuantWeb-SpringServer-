package com.quantweb.springserver.user.entity;

import com.quantweb.springserver.user_image.entity.UserImage;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.quantweb.springserver.user.user_status.UserStatus;
import com.quantweb.springserver.user.user_status.UserStatusConverter;

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


  @Column(name = "user_nickname")
  private String nickname;

  @Column(name = "user_name")
  private String name;

  @Column(name = "user_email")
  private String email;

  @Column(name = "user_phone_number")
  private String phoneNumber;

  @Column(name = "is_admin")
  private Boolean isAdmin;

  @Column(name = "user_latest_login_at")
  private LocalDateTime latestLoginAt;

  @Column(name = "user_created_at")
  private LocalDateTime createdAt;

  @Column(name = "user_deleted_at")
  private LocalDateTime deletedAt;
}
