package com.quantweb.springserver.domain.back_test.DTO;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.quantweb.springserver.common.entity.BaseTimeEntity;

import lombok.Data;

@Data
public class InvestmentResult extends BaseTimeEntity {

	@SerializedName("final_cumulative_return")
	private Float finalCumulativeReturn;

	@SerializedName("daily_cumulative_return")
	private DailyCumulativeReturn dailyCumulativeReturn;

	@SerializedName("mdd")
	private Map<String, Float> mdd;

	@SerializedName("max_drawdown_graph")
	private MaxDrawdownGraph maxDrawdownGraph;

	@SerializedName("average_stock_price")
	private ArrayList<AverageStockPriceItem> averageStockPrice;

	@SerializedName("creation_date")
	private String creationDate;

	@SerializedName("period")
	private Period period;

	@SerializedName("initial_amount")
	private Long initialAmount;

	@SerializedName("realized_profit")
	private Long realizedProfit;

	@SerializedName("unrealized_profit")
	private Long unrealizedProfit;

	@SerializedName("final_asset")
	private Long finalAsset;

	@SerializedName("annualized_return")
	private Float annualizedReturn;

	@SerializedName("transaction_history_graph")
	private ArrayList<TransactionHistoryGraphItem> transactionHistoryGraph;

	@Data
	public static class DailyCumulativeReturn {
		@SerializedName("backtest")
		private ArrayList<GraphItem> backtest;
		@SerializedName("us500")
		private ArrayList<GraphItem> us500;
	}

	@Data
	public static class GraphItem {
		private String date;
		@SerializedName(value = "return", alternate = {"mdd", "value"})
		private Float value;
	}

	@Data
	public static class MaxDrawdownGraph {
		@SerializedName("backtest")
		private ArrayList<GraphItem> backtest;
		@SerializedName("us500")
		private ArrayList<GraphItem> us500;
	}

	@Data
	public static class AverageStockPriceItem {
		private String date;
		@SerializedName("average_open")
		private Float averageOpen;
		@SerializedName("average_close")
		private Float averageClose;
	}

	@Data
	public static class Period {
		@SerializedName("start_date")
		private String startDate;
		@SerializedName("end_date")
		private String endDate;
	}

	@Data
	public static class TransactionHistoryGraphItem {
		private String date;
		private String action;
		private Long amount;
		private String ticker;
	}

	@Data
	public static class StrategyInfo {
		@SerializedName("strategy_name")
		private String strategyName;
		@SerializedName("rebalancing_period")
		private String rebalancingPeriod;
		@SerializedName("strategy_used")
		private String strategyUsed;
		private ArrayList<Stock> stock;
		@SerializedName("investment_sectors_pie_chart")
		private ArrayList<InvestmentSectorsPieChartItem> investmentSectorsPieChart;

		@Data
		public static class Stock {
			private String ticker;
			private String sector;
			private Float percentage;
		}

		@Data
		public static class InvestmentSectorsPieChartItem {
			private String sector;
			private Float percentage;
		}
	}
}
