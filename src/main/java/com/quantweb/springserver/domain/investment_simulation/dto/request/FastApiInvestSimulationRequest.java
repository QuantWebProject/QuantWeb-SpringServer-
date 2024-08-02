package com.quantweb.springserver.domain.investment_simulation.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FastApiInvestSimulationRequest {
    private String userName;
    private List<String> stockAndQuantity;
    private StrategySetup strategySetup;

    @Getter
    @AllArgsConstructor
    public static class StrategySetup {
        private String strategyName;
        private int stockSelection;
        private String rebalancingPeriod;
        private String ohlcv;
        private String backtestStrategy;
        private List<TechnicalAnalysisStrategy> technicalAnalysisStrategy;
        private ValueInvestmentStrategy valueInvestmentStrategy;
    }

    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TechnicalAnalysisStrategy {
        private StaticAssetAllocation staticAssetAllocation;
        private TatcticalAssetAllocation tatcticalAssetAllocation;
        private Macd macd;
        private TrendFollowing trendFollowing;
        private Rsi rsi;
        private BollingerBands bollingerBands;
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



    @Getter
    @AllArgsConstructor
    public static class ValueInvestmentStrategy {
        private List<Option> options;
    }

    @Getter
    @AllArgsConstructor
    public static class Option {
        private String factor;
        private List<Integer> percent;
        private List<Double> range;
    }
}
