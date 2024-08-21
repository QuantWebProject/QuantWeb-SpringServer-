package com.quantweb.springserver.domain.back_test.converter;


import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.request.BackTestRequestDto;
import com.quantweb.springserver.domain.back_test.DTO.response.BackTestDetailsDto;
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

    public static BackTest toBackTest(User user, BackTestRequestDto.BackTestSaveDto responseDto){

        return BackTest.builder()
                .user(user)
                .name(responseDto.getBackTestInfo().getName())
                .stockNum(responseDto.getBackTestInfo().getStockNum())
                .initInvestmentFund(responseDto.getInvestment_result().getInitial_amount())
                .fees(responseDto.getBackTestInfo().getFees())
                .rebalancePeriod(responseDto.getStrategy_info().getRebalancing_period())
                .investStartDate(LocalDate.parse(responseDto.getInvestment_result().getPeriod().getStart_date()))
                .investEndDate(LocalDate.parse(responseDto.getInvestment_result().getPeriod().getEnd_date()))
                .yearlyAverageProfit(responseDto.getInvestment_result().getAnnualized_return())
                .realizedProfit(responseDto.getInvestment_result().getRealized_profit())
                .unrealized_profit(responseDto.getInvestment_result().getUnrealized_profit())
                .finalAsset(responseDto.getInvestment_result().getFinal_asset())
                .marketShared(false)
                .deletedAt(null)
                .technicalStrategy(TechnicalStrategy.valueOf(responseDto.getStrategy_info().getStrategy_used()))
                .build();
    }


    public static BackTestDetailsDto.GetBackTestDto toBackTestResultDto(BackTest backTest,
                                                                         List<DailyPercentage> dailyPercentages, List<DailyPercentageUs500> dailyPercentageUs500s,
                                                                         List<Mdd> mdds, List<MddUs500> mddUs500s){

        BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn dailyCumulativeReturn = toDailyCumulativeReturn(dailyPercentages, dailyPercentageUs500s);

        BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph maxDrawdownGraph = toMaxDrawdownGraph(mdds, mddUs500s);

        return BackTestDetailsDto.GetBackTestDto.builder()
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
    public static BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn toDailyCumulativeReturn(List<DailyPercentage> dailyPercentages, List<DailyPercentageUs500> dailyPercentageUs500s){

        List<BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500> backtest = dailyPercentages.stream()
                .map(dp -> new BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500(dp.getDate(),dp.getReturns()))
                .sorted(Comparator.comparing(BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        List<BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500> us500 = dailyPercentageUs500s.stream()
                .map(dpu -> new BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500(dpu.getDate(), dpu.getReturns()))
                .sorted(Comparator.comparing(BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        return BackTestDetailsDto.GetBackTestDto.DailyCumulativeReturn.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }

    //mdd
    public static BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph toMaxDrawdownGraph(List<Mdd> mdds, List<MddUs500> mddUs500s){

        List<BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500> backtest = mdds.stream()
                .map(mdd -> new BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500(mdd.getDate(), mdd.getMdd()))
                .sorted(Comparator.comparing(BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500::getDate))
                .collect(Collectors.toList());


        List<BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500> us500 = mddUs500s.stream()
                .map(mddu -> new BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500(mddu.getDate(), mddu.getMdd()))
                .sorted(Comparator.comparing(BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.BackTestOrUs500::getDate))
                .collect(Collectors.toList());

        return BackTestDetailsDto.GetBackTestDto.MaxDrawdownGraph.builder()
                .backTest(backtest)
                .us500(us500)
                .build();
    }

    public static List<BackTestResponseDto.BackTestSaveDto> toBackTestList(List<BackTest> backTests){

        return backTests.stream()
                .map(bt -> new BackTestResponseDto.BackTestSaveDto(bt.getId(), bt.getName()))
                .toList();
    }
}
