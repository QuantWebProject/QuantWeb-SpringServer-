package com.quantweb.springserver.domain.stock.service;

import com.quantweb.springserver.domain.back_test.dto.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.stock.converter.StockConverter;
import com.quantweb.springserver.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    public void saveStock(List<StrategyInfoDto.Stock> stocks, BackTest backTest){

        stocks.forEach(st -> {
            stockRepository.save(StockConverter.toBackTestStock(st, backTest));
        });
    }

}
