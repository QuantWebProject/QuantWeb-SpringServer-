package com.quantweb.springserver.domain.back_test.converter;


import com.quantweb.springserver.domain.back_test.dto.request.BackTestInput;
import com.quantweb.springserver.domain.back_test.dto.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.dto.response.BackTestResponseDto.BackTestResultDto;
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

//    public static BackTestResponseDto.BackTestResultDto toBackTestResultDto(BackTest backTest){
//
//        return BackTestResponseDto.BackTestResultDto.builder()
////                .accumulatedProfit(backTest.getRealizedProfit())
////                .mdd()
////                .totalAssest()
////                .initInvestFund()
////                .evaluatedProfit()
////                .realizedProfit()
////                .marketShared()
////                .investCategories()
//                .build();
//    }


    public static BackTestResultDto toBackTestResultDto(BackTest backTest){
        BackTestResultDto.DailyCumulativeReturn dailyCumulativeReturn = toDailyCumulativeReturn();

        BackTestResultDto.MaxDrawdownGraph maxDrawdownGraph = toMaxDrawdownGraph();

        return BackTestResultDto.builder()
                .finalCumulativeReturn()
                .dailyCumulativeReturn(dailyCumulativeReturn)
                .mdd()
                .maxDrawdownGraph(maxDrawdownGraph)
                .startDate()
                .endDate()
                .finalAsset()
                .initialAmount()
                .evaluatedProfit()
                .realizedProfit()
                .marketShared()
                .build();
    }

    public static BackTestResultDto.DailyCumulativeReturn toDailyCumulativeReturn(){
        //누적 수익률
        BackTestResultDto.DailyCumulativeReturn.BackTestOrUs500 backtest = BackTestResultDto.DailyCumulativeReturn.BackTestOrUs500.builder()
                .date()
                .returns()
                .build();


        BackTestResultDto.DailyCumulativeReturn.BackTestOrUs500 us500 = BackTestResultDto.DailyCumulativeReturn.BackTestOrUs500.builder()
                .date()
                .returns()
                .build();

        return BackTestResultDto.DailyCumulativeReturn.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }

    public static BackTestResultDto.MaxDrawdownGraph toMaxDrawdownGraph(){
        //mdd
        BackTestResultDto.MaxDrawdownGraph.BackTestOrUs500 backtest = BackTestResultDto.MaxDrawdownGraph.BackTestOrUs500.builder()
                .date()
                .returns()
                .build();


        BackTestResultDto.MaxDrawdownGraph.BackTestOrUs500 us500 = BackTestResultDto.MaxDrawdownGraph.BackTestOrUs500.builder()
                .date()
                .returns()
                .build();

        return BackTestResultDto.MaxDrawdownGraph.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }
}
