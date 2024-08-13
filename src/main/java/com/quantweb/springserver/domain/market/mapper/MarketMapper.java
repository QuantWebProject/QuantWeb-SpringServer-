package com.quantweb.springserver.domain.market.mapper;


import com.quantweb.springserver.common.exception.CustomException;
import com.quantweb.springserver.common.exception.ErrorCode;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.investment_simulation.repository.InvestmentSimulationRepository;
import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.entity.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class MarketMapper {
    public Market toMarket(MarketCreateRequest request,BackTest backTest,InvestmentSimulation investmentSimulation){

        return Market.builder()
                .name(request.getName())
                .backTest(backTest)
                .investmentSimulation(investmentSimulation)
                .marketTags(request.getMarketTags())
                .build();
    }
}
