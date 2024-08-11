package com.quantweb.springserver.domain.stock.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StockResponseDto {

    private List<StockRatio> stocks;    //투자 종목들
    private List<InvestmentSectorsPieChart> investmentSectorsPieChart;    //투자종목 파이차트

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class StockRatio {
        private String ticker;  //주식 이름
        private String sector;  //종목 이름
        private Float percentage;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class InvestmentSectorsPieChart {
        private String sector;  //종목 이름
        private Float percentage;
    }
}
