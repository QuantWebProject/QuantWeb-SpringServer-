package com.quantweb.springserver.domain.stock.converter;

import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import com.quantweb.springserver.domain.stock.dto.response.StockResponseDto;
import com.quantweb.springserver.domain.stock.entity.Stock;
import java.util.List;

public class StockConverter {

  public static Stock toBackTestStock(StrategyInfoDto.Stock stock, BackTest backTest) {

    return Stock.builder()
        .stockName(stock.getTicker())
        .sector(stock.getSector())
        .percentage(stock.getPercentage())
        .backTest(backTest)
        .investmentSimulation(null)
        .build();
  }

  public static StockResponseDto toStockResponseDto(
      List<Stock> stocks, List<InvestmentSectorsPieChart> pieCharts) {

    List<StockResponseDto.StockRatio> stockRatioList =
        stocks.stream()
            .map(
                st ->
                    new StockResponseDto.StockRatio(
                        st.getStockName(), st.getSector(), st.getPercentage()))
            .toList();

    List<StockResponseDto.InvestmentSectorsPieChart> charts =
        pieCharts.stream()
            .map(
                pc ->
                    new StockResponseDto.InvestmentSectorsPieChart(
                        pc.getSector(), pc.getPercentage()))
            .toList();

    return StockResponseDto.builder()
        .stocks(stockRatioList)
        .investmentSectorsPieChart(charts)
        .build();
  }
}
