package com.quantweb.springserver.domain.user_image.entity;

import org.springframework.data.repository.Repository;

public interface UserImageRepository extends Repository<UserImage, Long> {

  UserImage save(UserImage userImage);
}
