package com.quantweb.springserver.domain.investment_simulation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TechnicalAnalysisStrategy {

  private StaticAssetAllocation staticAssetAllocation;
  private TatcticalAssetAllocation tatcticalAssetAllocation;
  private Macd macd;
  private TrendFollowing trendFollowing;
  private Rsi rsi;
  private BollingerBands bollingerBands;

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
