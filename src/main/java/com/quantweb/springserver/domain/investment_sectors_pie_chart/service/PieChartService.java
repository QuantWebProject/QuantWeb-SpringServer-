package com.quantweb.springserver.domain.investment_sectors_pie_chart.service;

import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.converter.PieChartConverter;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.repository.PieChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PieChartService {

    private final PieChartRepository pieChartRepository;

    @Transactional
    public void savePieChart(ArrayList<StrategyInfoDto.InvestmentSectorsPieChartItem> investmentSectorsPieChart, BackTest backTest){

        investmentSectorsPieChart.forEach(
                chart -> {
                    pieChartRepository.save(PieChartConverter.toBackTestPieChart(chart, backTest));
                }
        );
    }
}
