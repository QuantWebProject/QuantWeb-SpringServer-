package com.quantweb.springserver.domain.back_test.dto.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class BackTestInput {
	private String name;

	@SerializedName(value = "strategy_setup")
	private StrategySetup strategy_setup;

	@Data
	public static class StrategySetup {
		@SerializedName("strategy_name")
		private String strategy_name;

		@SerializedName("stock_selection")
		private Integer stock_selection;

		@SerializedName("period")
		private Period period;

		@SerializedName("initial_amount")
		private Float initial_amount;

		@SerializedName("fee")
		private Float fee;

		@SerializedName("rebalancing_period")
		private String rebalancing_period;

		@SerializedName("ohlcv")
		private String ohlcv;

		@SerializedName("backtest_strategy")
		private String backtest_strategy;

		@SerializedName("technical_analysis_strategy")
		private List<TechnicalAnalysisStrategy> technical_analysis_strategy;

		@SerializedName("value_investment_strategy")
		private ValueInvestmentStrategy value_investment_strategy;

		@Data
		public static class Period {
			@SerializedName("start_date")
			private LocalDate start_date;

			@SerializedName("end_date")
			private LocalDate end_date;
		}

	}

	@Data
	public static class TechnicalAnalysisStrategy {

		@SerializedName("static_analysis_strategy")
		private StaticAssetAllocation static_asset_allocation;

		@SerializedName("tactical_asset_allocation")
		private TacticalAssetAllocation tactical_asset_allocation;

		@SerializedName("macd")
		private MACD macd;

		@SerializedName("trend_following")
		private TrendFollowing trend_following;

		@SerializedName("rsi")
		private RSI rsi;

		@SerializedName("bollinger_band")
		private BollingerBand bollinger_bands;

		@Data
		public static class StaticAssetAllocation {
			private String rebalance;
		}

		@Data
		public static class TacticalAssetAllocation {
			private String rebalance;
			private Integer n_s;
			private Integer year_s;
		}

		@Data
		public static class MACD {
			private Integer fastperiods;
			private Integer slowperiods;
			private Integer signalperiods;
		}
		@Data
		public static class TrendFollowing {
			private Integer sma_period;
		}

		@Data
		public static class RSI {
			private Integer high_rsi;
			private Integer low_rsi;
		}
		@Data
		public static class BollingerBand {
			private Integer timeperiod_input;
			private Integer nbdevup_input;
			private Integer nbdevdn_input;
		}
	}

	@Data
	public static class ValueInvestmentStrategy {
		@SerializedName("options")
		private List<ValueInvestmentOption> options;
		@Data
		public static class ValueInvestmentOption {
			@SerializedName("factor")
			private String factor;

			@SerializedName("percent")
			private List<Float> percent;

			@SerializedName("range")
			private List<Float> range;
		}
	}
}
