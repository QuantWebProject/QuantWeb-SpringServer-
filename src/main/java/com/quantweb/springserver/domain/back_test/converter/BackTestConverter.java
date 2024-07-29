package com.quantweb.springserver.domain.back_test.converter;


import com.quantweb.springserver.domain.back_test.dto.request.BackTestInput;
import com.quantweb.springserver.domain.back_test.dto.response.BackTestResponseDto;
import com.quantweb.springserver.domain.back_test.dto.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.entity.TechnicalStrategy;

import java.time.LocalDate;


public class BackTestConverter {

    public static BackTest toBackTest(BackTestInput backTestInput, InvestmentResultDto responseDto){

        return BackTest.builder()
                //.user()
                .name(backTestInput.getName())
                .stockNum(backTestInput.getStrategy_setup().getStock_selection())
                .initInvestmentFund(responseDto.getInitial_amount())
                .fees(backTestInput.getStrategy_setup().getFee())
                .rebalancePeriod(backTestInput.getStrategy_setup().getRebalancing_period())
                .investStartDate(LocalDate.parse(responseDto.getPeriod().getStart_date()))
                .investEndDate(LocalDate.parse(responseDto.getPeriod().getEnd_date()))
                .yearlyAverageProfit(responseDto.getAnnualized_return())
                .realizedProfit(responseDto.getRealized_profit())
                .unrealized_profit(responseDto.getUnrealized_profit())
                .finalAsset(responseDto.getFinal_asset())
                .marketShared(false)
                .deletedAt(null)
                .technicalStrategy(TechnicalStrategy.valueOf(backTestInput.getStrategy_setup().getStrategy_name()))
                .build();
    }

    public static BackTestResponseDto.BackTestResultDto toBackTestResultDto(BackTest backTest){

        return BackTestResponseDto.BackTestResultDto.builder()
//                .accumulatedProfit(backTest.getRealizedProfit())
//                .mdd()
//                .totalAssest()
//                .initInvestFund()
//                .evaluatedProfit()
//                .realizedProfit()
//                .marketShared()
//                .investCategories()

                .build();
    }
}
