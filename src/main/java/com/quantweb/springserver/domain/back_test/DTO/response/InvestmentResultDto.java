package com.quantweb.springserver.domain.back_test.DTO.response;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class InvestmentResultDto {

		@SerializedName("final_cumulative_return")
		private Float final_cumulative_return;

		@SerializedName("daily_cumulative_return")
		private DailyCumulativeReturn daily_cumulative_return;

		@SerializedName("mdd")
		private Map<String, Float> mdd;

		@SerializedName("max_drawdown_graph")
		private MaxDrawdownGraph max_drawdown_graph;

//		@SerializedName("average_stock_price")
//		private ArrayList<AverageStockPriceItem> averageStockPrice;

		@SerializedName("creation_date")
		private String creation_date;

		@SerializedName("period")
		private Period period;

		@SerializedName("initial_amount")
		private Long initial_amount;

		@SerializedName("realized_profit")
		private Long realized_profit;

		@SerializedName("unrealized_profit")
		private Long unrealized_profit;

		@SerializedName("final_asset")
		private Long final_asset;

		@SerializedName("annualized_return")
		private Float annualized_return;

		@SerializedName("transaction_history_graph")
		private ArrayList<TransactionHistoryGraphItem> transaction_history_graph;

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
			private String start_date;
			@SerializedName("end_date")
			private String end_date;
		}

		@Data
		public static class TransactionHistoryGraphItem {
			private String date;
			private String action;
			private Long amount;
			private String ticker;
			private Float realized_profit;
			private Float profit;
			private Float fee;
			private Float total_amount;
		}

}
