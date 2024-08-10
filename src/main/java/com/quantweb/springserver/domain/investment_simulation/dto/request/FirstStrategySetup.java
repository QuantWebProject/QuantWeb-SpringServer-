package com.quantweb.springserver.domain.investment_simulation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FirstStrategySetup {
    private String strategyName;
    private int stockSelection;
    private String rebalancingPeriod;
    private String ohlcv;
    private String backtestStrategy;
    private TechnicalAnalysisStrategy technicalAnalysisStrategy;
    private ValueInvestmentStrategy valueInvestmentStrategy;
}
