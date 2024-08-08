package com.quantweb.springserver.domain.back_test.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackTestResultDto {

    private InvestmentResultDto investmentResultDto;
    private StrategyInfoDto strategyInfoDto;
}
