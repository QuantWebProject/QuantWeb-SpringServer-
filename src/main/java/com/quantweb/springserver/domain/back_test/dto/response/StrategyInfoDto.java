package com.quantweb.springserver.domain.back_test.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StrategyInfoDto {

//    @SerializedName("strategy_info")
//    private StrategyInfo strategy_info;

//    @Data
//    public static class StrategyInfo {
//        @SerializedName("strategy_name")
        private String strategy_name;
        @SerializedName("rebalancing_period")
        private String rebalancing_period;
        @SerializedName("strategy_used")
        private String strategy_used;
        private ArrayList<Stock> stock;
        @SerializedName("investment_sectors_pie_chart")
        private ArrayList<InvestmentSectorsPieChartItem> investment_sectors_pie_chart;

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
   // }
}
