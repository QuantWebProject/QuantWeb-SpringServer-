package com.quantweb.springserver.user.service.dto.response;

import lombok.Getter;
import com.quantweb.springserver.user_image.entity.UserImage;
import com.quantweb.springserver.user.entity.User;

@Getter
public class UserInfoResponse {

  private final Long id;
  private final String userStatus;
  private final UserImage userImage;
  private final String nickname;
  private final Integer age;
  private final String sex;
  private final String email;
  private final String phoneNumber;
  private final Boolean isAdmin;

  public UserInfoResponse(User user) {
    this.id = user.getId();
    this.userStatus = user.getUserStatus().getName();
    this.userImage = user.getUserImage();
    this.nickname = user.getNickname();
    this.age = user.getAge();
    this.sex = user.getSex();
    this.email = user.getEmail();
    this.phoneNumber = user.getPhoneNumber();
    this.isAdmin = user.getIsAdmin();
  }
}
