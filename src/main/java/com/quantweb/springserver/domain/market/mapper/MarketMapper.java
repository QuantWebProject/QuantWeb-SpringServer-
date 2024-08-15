package com.quantweb.springserver.domain.market.mapper;



import com.quantweb.springserver.common.exception.CustomException;
import com.quantweb.springserver.common.exception.ErrorCode;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.dto.response.MarketPagingResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketSummaryResponse;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_subscribe;
import com.quantweb.springserver.domain.market.respository.MarketSubscribeRepository;
import com.quantweb.springserver.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MarketMapper {

    private final MarketSubscribeRepository marketSubscribeRepository;
    public Market toMarket(MarketCreateRequest request,BackTest backTest,InvestmentSimulation investmentSimulation){

        return Market.builder()
                .name(request.getName())
                .backTest(backTest)
                .investmentSimulation(investmentSimulation)
                .marketTags(request.getMarketTags())
                .build();
    }

    public <T> MarketPagingResponse<T> toMarketPagingResponse(Page<T> market){
        return MarketPagingResponse.<T>builder()
                .backTests(market.getContent())
                .investmentSimulation(market.getContent())
                .page(market.getNumber())
                .totalPages(market.getTotalPages())
                .totalElements((int) market.getTotalElements())
                .isFirst(market.isFirst())
                .isLast(market.isFirst())
                .build();
    }

    public MarketSummaryResponse toMarketBackTestSummaryResponse(Market market, User user){
        BackTest backTest = market.getBackTest();

        return MarketSummaryResponse.builder()
                .strategyId(backTest.getId())
                .name(backTest.getName())
                .nickName(backTest.getUser().getName())
                //.finalCumulativeReturn()
                .startDate(backTest.getInvestStartDate())
                .endDate(backTest.getInvestEndDate())
                //.mdd()
                .subscribeStatus(getSubscribeStatus(market, user))
                .subscribeNum(market.getSubscribeNum())
                .build();
    }

    public MarketSummaryResponse toMarketInvestmentSimulationSummaryResponse(Market market, User user){
        InvestmentSimulation investmentSimulation = market.getInvestmentSimulation();

        return MarketSummaryResponse.builder()
                .strategyId(investmentSimulation.getId())
                .name(investmentSimulation.getName())
                .nickName(investmentSimulation.getUser().getName())
                //.finalCumulativeReturn()
                .startDate(investmentSimulation.getInvestStartDate())
                .endDate(investmentSimulation.getInvestEndDate())
                //.mdd()
                .subscribeStatus(getSubscribeStatus(market, user))
                .subscribeNum(market.getSubscribeNum())
                .build();
    }

    private Boolean getSubscribeStatus(Market market, User user){
        Optional <Market_subscribe> marketSubscribe = marketSubscribeRepository.findByUserAndMarket(user,market);

        return marketSubscribe.map(Market_subscribe::isChecked).orElse(false);
    }
}
