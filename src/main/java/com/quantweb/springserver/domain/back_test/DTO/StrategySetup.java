package com.quantweb.springserver.domain.back_test.DTO;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;


@Data
public class StrategySetup {
	@SerializedName("strategy_name")
	private String strategyName;

	@SerializedName("stock_selection")
	private Integer stockSelection;

	@SerializedName("period")
	private Period period;

	@SerializedName("initial_amount")
	private Float initialAmount;

	@SerializedName("fee")
	private Float fee;

	@SerializedName("rebalancing_period")
	private String rebalancingPeriod;

	@SerializedName("ohlcv")
	private String ohlcv;

	@SerializedName("backtest_strategy")
	private String backtestStrategy;

	@SerializedName("technical_analysis_strategy")
	private List<TechnicalAnalysisStrategy> technicalAnalysisStrategy;

	@SerializedName("value_investment_strategy")
	private ValueInvestmentStrategy valueInvestmentStrategy;
}

@Data
class Period {
	@SerializedName("start_date")
	private LocalDate startDate;

	@SerializedName("end_date")
	private LocalDate endDate;
}

@Data
class StaticAssetAllocation {
	@SerializedName("rebalance")
	private String rebalance;
}


@Data
class ValueInvestmentOption {
	@SerializedName("factor")
	private String factor;

	@SerializedName("percent")
	private List<Float> percent;

	@SerializedName("range")
	private List<Float> range;
}
