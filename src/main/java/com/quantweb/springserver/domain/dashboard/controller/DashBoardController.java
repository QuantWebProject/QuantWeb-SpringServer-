package com.quantweb.springserver.domain.dashboard.controller;

import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestRecommendationResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import com.quantweb.springserver.domain.dashboard.dto.BackTestSubscribeResponse;
import com.quantweb.springserver.domain.dashboard.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public static final int PAGE_SIZE = 8;


  @Authenticated
  @GetMapping("/backtest")
  public ResponseEntity<Page<MyBackTestResponse>> getMyBackTest(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam String type,
                                                                @AuthenticationPrincipal Long userId) {

    Pageable pageable = PageRequest.of(page, PAGE_SIZE);

    Page<MyBackTestResponse> response = dashBoardService.getMyBackTest(pageable,userId, type);

    return ResponseEntity.ok(response);
  }

  @Authenticated
  @GetMapping("/recommendation")
  public ResponseEntity<Page<MyBackTestRecommendationResponse>> getRecommendationBackTest(@RequestParam(defaultValue = "0") int page,
                                                                             @AuthenticationPrincipal Long userId) {
    Pageable pageable = PageRequest.of(page, PAGE_SIZE);

    Page<MyBackTestRecommendationResponse> response = dashBoardService.getRecommendationBackTest(pageable, userId);

    return ResponseEntity.ok(response);
  }
}
