package com.quantweb.springserver.user.controller;

import com.quantweb.springserver.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.user.service.UserService;
import com.quantweb.springserver.user.service.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;


    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> retrieveUserInfo(@AuthenticationPrincipal Long userId) {
        UserInfoResponse response = service.retrieveUserInfo(userId);

        return ResponseEntity.ok(response);
    }

}
