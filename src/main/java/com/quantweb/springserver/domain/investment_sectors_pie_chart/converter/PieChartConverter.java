package com.quantweb.springserver.domain.investment_sectors_pie_chart.converter;

import com.quantweb.springserver.domain.back_test.dto.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;

public class PieChartConverter {

    public static InvestmentSectorsPieChart toBackTestPieChart(StrategyInfoDto.InvestmentSectorsPieChartItem investmentSectorsPieChart, BackTest backTest){

        return InvestmentSectorsPieChart.builder()
                .sector(investmentSectorsPieChart.getSector())
                .percentage(investmentSectorsPieChart.getPercentage())
                .backTest(backTest)
                .investmentSimulation(null)
                .build();
    }
}
