package com.quantweb.springserver.user_image.entity;

import org.springframework.data.repository.Repository;

public interface UserImageRepository extends Repository<UserImage, Long> {
  UserImage save(UserImage userImage);
}
