package com.quantweb.springserver.domain.back_test.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class TechnicalAnalysisStrategy {
	@SerializedName("technical_analysis_strategy_id")
	private Long id;

	@SerializedName("back_test_id")
	private Long backTestId;

	@SerializedName("static_analysis_strategy")
	private StaticAnalysisStrategy staticAnalysisStrategy;

	@SerializedName("tactical_asset_allocation")
	private TacticalAssetAllocation tacticalAssetAllocation;

	@SerializedName("macd")
	private MACD macd;

	@SerializedName("trend_following")
	private TrendFollowing trendFollowing;

	@SerializedName("rsi")
	private RSI rsi;

	@SerializedName("bollinger_band")
	private BollingerBand bollingerBand;

	@SerializedName("investment_simulation_id")
	private Long investmentSimulationId;
}

@Data
class StaticAnalysisStrategy {
	private String rebalance;
}

@Data
class TacticalAssetAllocation {
	private String rebalance;
	private Integer n_s;
	private Integer year_s;
}

@Data
class MACD {
	private Integer fastPeriod;
	private Integer slowPeriod;
	private Integer signalPeriod;
}
@Data
class TrendFollowing {
	private Integer smaPeriod;
}

@Data
class RSI {
	private Integer highRsi;
	private Integer lowRsi;
}
@Data
class BollingerBand {
	private Integer timePeriodInput;
	private Integer nbDevUpInput;
	private Integer nbDevDnInput;
}

