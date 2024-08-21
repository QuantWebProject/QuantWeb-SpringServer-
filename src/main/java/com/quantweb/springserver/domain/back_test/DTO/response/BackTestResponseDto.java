package com.quantweb.springserver.domain.back_test.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BackTestResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BackTestCreateDto{
        private InvestmentResultDto investment_result;
        private StrategyInfoDto strategy_info;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BackTestSaveDto{
        private Long backtestId;
        private String backtestName;

    }


}
