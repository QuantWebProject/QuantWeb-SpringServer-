package com.quantweb.springserver.domain.back_test.dto.response;

import com.quantweb.springserver.domain.stock.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class BackTestResponseDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class BackTestResultDto{

        private Float accumulatedProfit;    //누적수익률
        private Float mdd;  //최대 손실
        private Integer totalAssest;    //총자산
        private Integer initInvestFund; //투자원금
        private Integer evaluatedProfit;    //평가 손익
        private Integer realizedProfit; //실현손익
        private Boolean marketShared;
        private List<Stock> investCategories;  //투자종목


    }
}
