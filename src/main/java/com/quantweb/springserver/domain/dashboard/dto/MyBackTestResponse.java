package com.quantweb.springserver.domain.dashboard.dto;

import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyBackTestResponse {
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
}