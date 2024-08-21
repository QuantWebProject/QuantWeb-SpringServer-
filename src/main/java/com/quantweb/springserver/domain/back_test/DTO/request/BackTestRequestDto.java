package com.quantweb.springserver.domain.back_test.DTO.request;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.TechnicalStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BackTestRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BackTestSaveDto{
        private BackTestInfo backTestInfo;
        private InvestmentResultDto investment_result;
        private StrategyInfoDto strategy_info;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BackTestInfo {
            private String name;
            private Integer stockNum;
            private Float fees;
        }
    }
}
