package com.quantweb.springserver.domain.dashboard.controller;

import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.dashboard.service.DashBoardService;
import com.quantweb.springserver.domain.market.entity.Market;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashBoardController {

  private final DashBoardService dashBoardService;

  @Authenticated
  @GetMapping("/me")
  public ResponseEntity< List<Market>> getMyBackTest(@AuthenticationPrincipal Long userId) {
    List<Market> response = dashBoardService.getMyBackTest(userId);

    return ResponseEntity.ok(response);
  }
}
