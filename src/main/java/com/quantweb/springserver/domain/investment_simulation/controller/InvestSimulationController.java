package com.quantweb.springserver.domain.investment_simulation.controller;

import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.investment_simulation.dto.request.FirstInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.request.RenewInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.service.InvestSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invest-simulation")
public class InvestSimulationController {

  private final InvestSimulationService investSimulationService;

  @GetMapping("/first")
  public ResponseEntity<?> firstInvestSimulation(@AuthenticationPrincipal Long userId, @RequestBody FirstInvestSimulationRequest request) {
     investSimulationService.firstInvestSimulation(userId,request);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/renew")
  public ResponseEntity<?> renewInvestSimulation(@AuthenticationPrincipal Long userId, @RequestBody RenewInvestSimulationRequest request) {
    investSimulationService.renewInvestSimulation(userId,request);

    return ResponseEntity.ok().build();
  }
}
