package com.quantweb.springserver.domain.stock.converter;

import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.stock.entity.Stock;

public class StockConverter {

    public static Stock toBackTestStock(StrategyInfoDto.Stock stock, BackTest backTest){

        return Stock.builder()
                .stockName(stock.getTicker())
                .sector(stock.getSector())
                .percentage(stock.getPercentage())
                .backTest(backTest)
                .investmentSimulation(null)
                .build();
    }
}
