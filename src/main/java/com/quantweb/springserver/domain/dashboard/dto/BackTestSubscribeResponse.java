package com.quantweb.springserver.domain.dashboard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BackTestSubscribeResponse {
    private Long marketId;
    private String name;
    private LocalDate startedDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private Float yearlyProfit;
    private Float mdd;
    private Long initInvestmentFund;
    private Long unrealizedProfit;
    private Integer subscribeNum;
}
