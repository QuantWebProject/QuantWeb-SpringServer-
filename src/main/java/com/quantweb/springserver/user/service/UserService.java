package com.quantweb.springserver.user.service;

import com.quantweb.springserver.user.entity.User;
import com.quantweb.springserver.user.entity.UserRepository;
import com.quantweb.springserver.user.service.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public UserInfoResponse retrieveUserInfo(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다"));

    return new UserInfoResponse(user);
  }

}
