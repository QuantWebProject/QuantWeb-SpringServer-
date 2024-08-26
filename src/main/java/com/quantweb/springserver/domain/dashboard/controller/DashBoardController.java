package com.quantweb.springserver.domain.dashboard.controller;

import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import com.quantweb.springserver.domain.dashboard.dto.BackTestSubscribeResponse;
import com.quantweb.springserver.domain.dashboard.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashBoardController {

  private final DashBoardService dashBoardService;

  @Authenticated
  @GetMapping("/backtest")
  public ResponseEntity<MyBackTestResponse> getMyBackTest(@AuthenticationPrincipal Long userId) {
    MyBackTestResponse response = dashBoardService.getMyBackTest(userId, "latest");

    return ResponseEntity.ok(response);
  }

  @Authenticated
  @GetMapping("/recommendation")
  public ResponseEntity<BackTestSubscribeResponse> getRecommendationBackTest(@AuthenticationPrincipal Long userId) {
    BackTestSubscribeResponse response = dashBoardService.getRecommendationBackTest(userId,"subscribe");

    return ResponseEntity.ok(response);
  }
}
