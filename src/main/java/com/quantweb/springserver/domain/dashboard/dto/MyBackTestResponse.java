package com.quantweb.springserver.domain.dashboard.dto;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.text.html.Option;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@NoArgsConstructor
public class MyBackTestResponse {
    private List<MarketResponse> marketResponses;

    public MyBackTestResponse(List<BackTest> backTests, MarketRepository marketRepository){
        this.marketResponses = backTests.stream().map(
                backTest -> {
                    return MarketResponse.builder()
                        .backTestId(backTest.getId())
                        .name(backTest.getName())
                        .startedDate(backTest.getInvestStartDate())
                        .endDate(backTest.getInvestEndDate())
                        .initInvestmentFund(backTest.getInitInvestmentFund())
                        .yearlyProfit(backTest.getYearlyAverageProfit())
                        .unrealizedProfit(backTest.getUnrealized_profit())
                        .mdd(backTest.getGraph().getMdds().stream().map(mdd -> mdd.getMdd())
                                .min(Float::compareTo).get())
                        .marketShared(backTest.getMarketShared())
                        .createdAt(backTest.getCreatedAt())
                        .pieCharts(backTest.getPieCharts().stream().map(
                                    investmentSectorsPieChart -> InvestmentSectorsPieChart.builder()
                                            .sector(investmentSectorsPieChart.getSector())
                                            .percentage(investmentSectorsPieChart.getPercentage())
                                            .build()
                            ).collect(Collectors.toList()))
                        .build();}
        ).collect(Collectors.toList());
    }

    @Getter
    @Builder
    public static class MarketResponse {
        private Long backTestId;
        private String name;
        private LocalDate startedDate;
        private LocalDate endDate;
        private LocalDateTime createdAt;
        private Float yearlyProfit;
        private List<InvestmentSectorsPieChart> pieCharts;
        private Float mdd;
        private Long initInvestmentFund;
        private Long unrealizedProfit;
        private Boolean marketShared;
        private Integer popularity;
    }
}
