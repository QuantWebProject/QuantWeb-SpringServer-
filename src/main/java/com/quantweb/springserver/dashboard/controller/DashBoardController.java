package com.quantweb.springserver.dashboard.controller;

import com.quantweb.springserver.dashboard.controller.response.MyBackInvestResponse;
import com.quantweb.springserver.dashboard.controller.response.MyBackTestResponse;
import com.quantweb.springserver.dashboard.controller.response.RecommendInvestResponse;
import com.quantweb.springserver.dashboard.controller.response.SubscribeInvestResponse;
import com.quantweb.springserver.dashboard.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Authenticated
@RequestMapping("/api/dashboard")
@RestController
public class DashBoardController {

  private final DashBoardService dashboardService;


  @GetMapping("/backtest")
  public ResponseEntity<MyBackTestResponse> findMyBackTest(@AuthenticationPrincipal Long id) {
    MyBackTestResponse response = dashboardService.findMyBackTest(id);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/investment")
  public ResponseEntity<MyBackInvestResponse> findMyInvestment(@AuthenticationPrincipal Long id) {
    MyBackInvestResponse response = dashboardService.findMyInvestment(id);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/recommendation")
  public ResponseEntity<RecommendInvestResponse> findRecommendInvestment(@AuthenticationPrincipal Long id) {
    RecommendInvestResponse response = dashboardService.findRecommandInvestment(id);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/subscribe")
  public ResponseEntity<SubscribeInvestResponse> findSubscribeInvestment(@AuthenticationPrincipal Long id) {
    SubscribeInvestResponse response = dashboardService.findSubscribeInvestment(id);

    return ResponseEntity.ok(response);
  }

}
