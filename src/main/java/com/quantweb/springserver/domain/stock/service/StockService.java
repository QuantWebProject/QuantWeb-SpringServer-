package com.quantweb.springserver.domain.stock.service;

import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.repository.PieChartRepository;
import com.quantweb.springserver.domain.stock.converter.StockConverter;
import com.quantweb.springserver.domain.stock.dto.response.StockResponseDto;
import com.quantweb.springserver.domain.stock.entity.Stock;
import com.quantweb.springserver.domain.stock.repository.StockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

  private final StockRepository stockRepository;
  private final PieChartRepository pieChartRepository;

  public void saveStock(List<StrategyInfoDto.Stock> stocks, BackTest backTest) {

    stocks.forEach(
        st -> {
          stockRepository.save(StockConverter.toBackTestStock(st, backTest));
        });
  }

  public StockResponseDto getInvestmentSectors(Long backtestId) {

    List<Stock> stocks =
        stockRepository
            .findAllByBackTestId(backtestId)
            .orElseThrow(() -> new RuntimeException("백테스트가 존재하지 않습니다."));

    List<InvestmentSectorsPieChart> investmentSectorsPieCharts =
        pieChartRepository
            .findAllByBackTestId(backtestId)
            .orElseThrow(() -> new RuntimeException("백테스트가 존재하지 않습니다."));

    return StockConverter.toStockResponseDto(stocks, investmentSectorsPieCharts);
  }
}
