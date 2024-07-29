package com.quantweb.springserver.domain.graph.converter;

import com.quantweb.springserver.domain.back_test.dto.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.graph.entity.*;

import java.time.LocalDate;

public class GraphConverter {

    public static Graph toBackTestGraph(BackTest findBackTest){

        return Graph.builder()
                .backTest(findBackTest)
                .investmentSimulation(null)
                .build();
    }



    public static DailyPercentage toDailyPercentage(InvestmentResultDto.GraphItem backtest, Graph graph) {

        return DailyPercentage.builder()
                .date(LocalDate.parse(backtest.getDate()))
                .returns(backtest.getValue())
                .graph(graph)
                .build();
    }

    public static DailyPercentageUs500 toDailyPercentageUs500(InvestmentResultDto.GraphItem us500, Graph graph) {

        return DailyPercentageUs500.builder()
                .date(LocalDate.parse(us500.getDate()))
                .returns(us500.getValue())
                .graph(graph)
                .build();
    }

    public static Mdd toMdd(InvestmentResultDto.GraphItem mdd, Graph graph) {

        return Mdd.builder()
                .date(LocalDate.parse(mdd.getDate()))
                .mdd(mdd.getValue())
                .graph(graph)
                .build();
    }

    public static MddUs500 toMddUs500(InvestmentResultDto.GraphItem us500, Graph graph) {

        return MddUs500.builder()
                .date(LocalDate.parse(us500.getDate()))
                .mdd(us500.getValue())
                .graph(graph)
                .build();
    }
}
