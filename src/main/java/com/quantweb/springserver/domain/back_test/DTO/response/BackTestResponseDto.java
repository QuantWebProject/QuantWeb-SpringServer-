package com.quantweb.springserver.domain.back_test.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class BackTestResponseDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class GetBackTestDto {

        private Float finalCumulativeReturn;    //누적수익률
        private Float finalCumulativeReturnUs500;    //누적수익률 Us500
        private DailyCumulativeReturn dailyCumulativeReturn;    //누적수익률 - 그래프

        private Float mdd;  //최대 손실
        private Float mddUs500;  //최대 손실 Us500
        private MaxDrawdownGraph maxDrawdownGraph;  //최대 손실 - 그래프

        private LocalDate startDate;    //투자 시작 시간
        private LocalDate endDate;    //투자 끝 시간

        private Long finalAsset;    //총자산
        private Long initialAmount; //투자원금
        private Long unrealizedProfit;    //평가 손익
        private Long realizedProfit; //실현손익

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
                private LocalDate date;
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
                private LocalDate date;
                private Float mdd;
            }
        }
    }
}
