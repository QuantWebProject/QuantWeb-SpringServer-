package com.quantweb.springserver.domain.back_test.dto.response;

import com.quantweb.springserver.domain.stock.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.awt.datatransfer.FlavorEvent;
import java.time.LocalDateTime;
import java.util.List;

public class BackTestResponseDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class BackTestResultDto{

        private Float finalCumulativeReturn;    //누적수익률
        private DailyCumulativeReturn dailyCumulativeReturn;    //누적수익률 - 그래프

        private Float mdd;  //최대 손실
        private MaxDrawdownGraph maxDrawdownGraph;  //최대 손실 - 그래프

        private LocalDateTime startDate;    //투자 시작 시간
        private LocalDateTime endDate;    //투자 끝 시간

        private Integer finalAsset;    //총자산
        private Integer initialAmount; //투자원금
        private Integer evaluatedProfit;    //평가 손익
        private Integer realizedProfit; //실현손익

        private Boolean marketShared;   //마켓에 공유 되어있는지

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Builder
        public static class DailyCumulativeReturn {
            private List<BackTestOrUs500> backTest;
            private List<BackTestOrUs500> us500;

            @NoArgsConstructor
            @AllArgsConstructor
            @Getter
            @Builder
            public static class BackTestOrUs500 {
                private LocalDateTime date;
                private Float returns;
            }
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Builder
        public static class MaxDrawdownGraph {
            private List<BackTestOrUs500> backTest;
            private List<BackTestOrUs500> us500;

            @NoArgsConstructor
            @AllArgsConstructor
            @Getter
            @Builder
            public static class BackTestOrUs500 {
                private LocalDateTime date;
                private Float mdd;
            }
        }
    }
}
