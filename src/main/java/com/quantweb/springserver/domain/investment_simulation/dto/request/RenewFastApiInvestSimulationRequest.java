package com.quantweb.springserver.domain.investment_simulation.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class RenewFastApiInvestSimulationRequest {
    private String userName;
    private List<String> stockAndQuantity;
    private RenewStrategySetup strategySetup;
}
