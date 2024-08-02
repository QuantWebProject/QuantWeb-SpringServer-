package com.quantweb.springserver.domain.investment_simulation.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FirstInvestSimulationRequest {
    private int stockSelection;
    private String startDate;
    private String endDate;
    private String initialAmount;
    private String fee;
    private String rebalancingPeriod;
    private String backtestStrategy;
    private List<TechnicalAnalysisStrategy> technicalAnalysisStrategy;

    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TechnicalAnalysisStrategy {
        private FastApiInvestSimulationRequest.StaticAssetAllocation staticAssetAllocation;
        private FastApiInvestSimulationRequest.TatcticalAssetAllocation tatcticalAssetAllocation;
        private FastApiInvestSimulationRequest.Macd macd;
        private FastApiInvestSimulationRequest.TrendFollowing trendFollowing;
        private FastApiInvestSimulationRequest.Rsi rsi;
        private FastApiInvestSimulationRequest.BollingerBands bollingerBands;
    }

    @Getter
    @AllArgsConstructor
    public static class StaticAssetAllocation {
        private String rebalance;
    }

    @Getter
    @AllArgsConstructor
    public static class TatcticalAssetAllocation {
        private String rebalance;
        private String years;
    }

    @Getter
    @AllArgsConstructor
    public static class Macd {
        private String fastPeriod;
        private String slowPeriod;
        private String signalPeriod;
    }

    @Getter
    @AllArgsConstructor
    public static class TrendFollowing {
        private String smaPeriod;
    }

    @Getter
    @AllArgsConstructor
    public static class Rsi {
        private String highRsi;
        private String lowRsi;
    }

    @Getter
    @AllArgsConstructor
    public static class BollingerBands {
        private String timePeriodInput;
        private String nbdevupInput;
        private String nbdevdnInput;
    }



}
