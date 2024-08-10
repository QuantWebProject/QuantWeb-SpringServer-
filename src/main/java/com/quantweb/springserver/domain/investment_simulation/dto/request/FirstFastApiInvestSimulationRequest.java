package com.quantweb.springserver.domain.investment_simulation.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FirstFastApiInvestSimulationRequest {
  private String userName;
  private List<String> stockAndQuantity;
  private FirstStrategySetup strategySetup;
}
