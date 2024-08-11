package com.quantweb.springserver.domain.back_test.converter;


import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.response.BackTestResponseDto;
import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.entity.TechnicalStrategy;
import com.quantweb.springserver.domain.graph.entity.DailyPercentage;
import com.quantweb.springserver.domain.graph.entity.DailyPercentageUs500;
import com.quantweb.springserver.domain.graph.entity.Mdd;
import com.quantweb.springserver.domain.graph.entity.MddUs500;
import com.quantweb.springserver.domain.user.entity.User;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BackTestConverter {

    public static BackTest toBackTest(User user, BackTestInput backTestInput, InvestmentResultDto responseDto){

        return BackTest.builder()
                .user(user)
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


    public static BackTestResponseDto.GetBackTestDto toBackTestResultDto(BackTest backTest,
                                                                         List<DailyPercentage> dailyPercentages, List<DailyPercentageUs500> dailyPercentageUs500s,
                                                                         List<Mdd> mdds, List<MddUs500> mddUs500s){

        BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn dailyCumulativeReturn = toDailyCumulativeReturn(dailyPercentages, dailyPercentageUs500s);

        BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph maxDrawdownGraph = toMaxDrawdownGraph(mdds, mddUs500s);

        return BackTestResponseDto.GetBackTestDto.builder()
                .dailyCumulativeReturn(dailyCumulativeReturn)
                .finalCumulativeReturn(dailyCumulativeReturn.getBackTest().get(dailyCumulativeReturn.getBackTest().size()-1).getReturns())
                .finalCumulativeReturnUs500(dailyCumulativeReturn.getUs500().get(dailyCumulativeReturn.getUs500().size()-1).getReturns())
                .mdd(maxDrawdownGraph.getBackTest().get(maxDrawdownGraph.getBackTest().size()-1).getMdd())
                .mddUs500(maxDrawdownGraph.getUs500().get(maxDrawdownGraph.getUs500().size()-1).getMdd())
                .maxDrawdownGraph(maxDrawdownGraph)
                .startDate(backTest.getInvestStartDate())
                .endDate(backTest.getInvestEndDate())
                .finalAsset(backTest.getFinalAsset())
                .initialAmount(backTest.getInitInvestmentFund())
                .unrealizedProfit(backTest.getUnrealized_profit())
                .realizedProfit(backTest.getRealizedProfit())
                .marketShared(backTest.getMarketShared())
                .build();
    }

    //누적 수익률
    public static BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn toDailyCumulativeReturn(List<DailyPercentage> dailyPercentages, List<DailyPercentageUs500> dailyPercentageUs500s){

        List<BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500> backtest = dailyPercentages.stream()
                .map(dp -> new BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500(dp.getDate(),dp.getReturns()))
                .sorted(Comparator.comparing(BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        List<BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500> us500 = dailyPercentageUs500s.stream()
                .map(dpu -> new BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500(dpu.getDate(), dpu.getReturns()))
                .sorted(Comparator.comparing(BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        return BackTestResponseDto.GetBackTestDto.DailyCumulativeReturn.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }

    //mdd
    public static BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph toMaxDrawdownGraph(List<Mdd> mdds, List<MddUs500> mddUs500s){

        List<BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500> backtest = mdds.stream()
                .map(mdd -> new BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500(mdd.getDate(), mdd.getMdd()))
                .sorted(Comparator.comparing(BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500::getDate))
                .collect(Collectors.toList());


        List<BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500> us500 = mddUs500s.stream()
                .map(mddu -> new BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500(mddu.getDate(), mddu.getMdd()))
                .sorted(Comparator.comparing(BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        return BackTestResponseDto.GetBackTestDto.MaxDrawdownGraph.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }
}
