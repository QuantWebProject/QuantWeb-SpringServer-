package com.quantweb.springserver.domain.dashboard.dto;

import com.quantweb.springserver.domain.market.entity.Market;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarketSubscribeResponse {
    private List<MarketResponse> marketResponses;

    public MarketSubscribeResponse(List<Market> marketList){
        this.marketResponses = marketList.stream().map(
                market -> MarketResponse.builder()
                        .marketId(market.getId())
                        .name(market.getName())
                        .startedDate(market.getBackTest().getInvestStartDate())
                        .endDate(market.getBackTest().getInvestEndDate())
                        .initInvestmentFund(market.getBackTest().getInitInvestmentFund())
                        .yearlyProfit(market.getBackTest().getYearlyAverageProfit())
                        .unrealizedProfit(market.getBackTest().getUnrealized_profit())
                        .mdd(market.getBackTest().getGraph().getMdds().stream().map(mdd -> mdd.getMdd())
                                .max(Float::compareTo).get())
                        .subscribeNum(market.getSubscribeNum())
                        .build()
        ).collect(Collectors.toList());

    }

    @Getter
    @Builder
    private static class MarketResponse {
        private Long marketId;
        private String name;
        private LocalDate startedDate;
        private LocalDate endDate;
        private Float yearlyProfit;
        private Float mdd;
        private Long initInvestmentFund;
        private Long unrealizedProfit;
        private Integer subscribeNum;
    }
}
