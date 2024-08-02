package com.quantweb.springserver.domain.sales_transaction_history.converter;

import com.quantweb.springserver.domain.sales_transaction_history.dto.response.SalesHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;

import java.util.List;

public class SalesTransactionHistoryConverter {

    public static SalesHistoryResponseDto toHistoryResponseDto(SalesTransactionHistory history){

        List<SalesHistoryResponseDto.TransactionHistoryGraph> graph;

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
                .transactionHistoryGraph(graph)
                .build();
    }
}
