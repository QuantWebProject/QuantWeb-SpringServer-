package com.quantweb.springserver.domain.market.dto.request;


import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.market.entity.Market_tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketCreateRequest {
    private String name;
    private Long backTestId;
    private Long investmentSimulationId;
    private List<Market_tag> marketTags;
}
