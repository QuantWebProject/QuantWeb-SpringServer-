package com.quantweb.springserver.domain.investment_simulation.dto.request;

import com.quantweb.springserver.domain.value_investing_strategy.entity.ValueInvestingStrategy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FirstInvestSimulationRequest {
  private int stockSelection;
  private String strategyName;
  private String startDate;
  private String endDate;
  private String initialAmount;
  private String fee;
  private String rebalancingPeriod;
  private String backtestStrategy;
  private FirstStrategySetup technicalAnalysisStrategy;
  private List<ValueInvestingStrategy> valueInvestmentStrategy;
}
