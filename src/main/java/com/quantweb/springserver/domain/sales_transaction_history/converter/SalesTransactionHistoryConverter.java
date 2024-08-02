package com.quantweb.springserver.domain.sales_transaction_history.converter;

import com.quantweb.springserver.domain.sales_transaction_history.dto.response.SalesHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;

public class SalesTransactionHistoryConverter {

    public static SalesHistoryResponseDto toHistoryResponseDto(SalesTransactionHistory history){

        return SalesHistoryResponseDto.builder()
//                .transactionDate()
//                .stockName()
//                .period()
//                .realizedProfit()
//                .profitPercentage()
//                .buyQuantity()
//                .sellQuantity()
//                .buyPrice()
//                .sellPrice()
//                .status()
//                .fee()
                .build();
    }
}
