package com.quantweb.springserver.domain.investment_simulation.service;

import com.quantweb.springserver.domain.investment_simulation.dto.request.FirstInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.request.RenewInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.response.FirstFastApiInvestSimulationResponse;
import com.quantweb.springserver.domain.investment_simulation.dto.response.RenewFastApiInvestSimulationResponse;
import com.quantweb.springserver.domain.user.entity.User;
import com.quantweb.springserver.domain.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestSimulationService {

  private final InvestSimulationConnector investSimulationConnector;
  private final UserRepository userRepository;

  public void firstInvestSimulation(Long userId, FirstInvestSimulationRequest request) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    FirstFastApiInvestSimulationResponse firstFastApiInvestSimulationResponse =
        investSimulationConnector.requestFirstInvestSimulation(user, request);
  }

  public void renewInvestSimulation(Long userId, RenewInvestSimulationRequest request) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    float fee = 0;
    int initialAmount = 0;
    RenewFastApiInvestSimulationResponse renewFastApiInvestSimulationResponse =
        investSimulationConnector.requestRenewInvestSimulation(user, request, initialAmount, fee);
  }
}
