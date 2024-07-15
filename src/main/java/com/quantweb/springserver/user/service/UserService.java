package com.quantweb.springserver.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quantweb.springserver.user.entity.User;
import com.quantweb.springserver.user.entity.UserRepository;
import com.quantweb.springserver.user.service.dto.response.UserInfoResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;

  public UserInfoResponse retrieveUserInfo(Long userId) {
    User user = userRepository.findByUserId(userId);

    return new UserInfoResponse(user);
  }

}
