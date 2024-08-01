package com.quantweb.springserver.domain.user.service.dto.response;

import com.quantweb.springserver.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {

    private final Long id;
    private final String userStatus;
    private final String nickname;
    private final String email;
    private final Boolean isAdmin;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.userStatus = user.getUserStatus().getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.isAdmin = user.getIsAdmin();
    }
}
