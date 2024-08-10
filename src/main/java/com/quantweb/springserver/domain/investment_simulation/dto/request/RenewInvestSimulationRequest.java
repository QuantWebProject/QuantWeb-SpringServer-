package com.quantweb.springserver.domain.investment_simulation.dto.request;

import com.quantweb.springserver.domain.value_investing_strategy.entity.ValueInvestingStrategy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RenewInvestSimulationRequest {
    private int stockSelection;
    private String strategyName;
    private String fee;
    private String rebalancingPeriod;
    private String backtestStrategy;
    private TechnicalAnalysisStrategy technicalAnalysisStrategy;
}
