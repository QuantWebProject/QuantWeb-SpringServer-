package com.quantweb.springserver.domain.sales_transaction_history.converter;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.sales_transaction_history.dto.response.TransactionHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import com.quantweb.springserver.domain.sales_transaction_history.entity.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistoryConverter {

    public static SalesTransactionHistory toTransactionHistory(InvestmentResultDto.TransactionHistoryGraphItem transactionHistory, BackTest backTest){

        return SalesTransactionHistory.builder()
                .dateTime(LocalDate.parse(transactionHistory.getDate()))
                .quantity(transactionHistory.getAmount())
                .ticker(transactionHistory.getTicker())
                .type(TransactionType.valueOf(transactionHistory.getAction()))
                .backTest(backTest)
                .investmentSimulation(null)
                .build();
    }

    public static TransactionHistoryResponseDto.TransactionHistory toTransactionHistoryResult(SalesTransactionHistory transactionHistories){

        return TransactionHistoryResponseDto.TransactionHistory.builder()
//                .transactionDate(transactionHistories.getDateTime())
//                .stockName(transactionHistories.getTicker())
//                .period()
//                .realizedProfit()
//                .profitPercentage()
//                .buyQuantity()
//                .buyPrice()
//                .sellQuantity()
//                .sellPrice()
//                .status()
//                .fee()
//                .transactionHistoryGraph()
                .build();
    }

    public static TransactionHistoryResponseDto.TransactionHistoryGraph toTransactionHistoryGraph(SalesTransactionHistory transactionHistories){

        return TransactionHistoryResponseDto.TransactionHistoryGraph.builder()
                .date(transactionHistories.getDateTime())
                .amount(transactionHistories.getQuantity())
                .action(transactionHistories.getType())
                .ticker(transactionHistories.getTicker())
                .build();
    }

    public static TransactionHistoryResponseDto toTransactionHistoryResultDto(List<SalesTransactionHistory> transactionHistories){

        List<TransactionHistoryResponseDto.TransactionHistoryGraph> graphs = transactionHistories.stream()
                .map(TransactionHistoryConverter::toTransactionHistoryGraph)
                .toList();

        return TransactionHistoryResponseDto.builder()
                //.transactionHistories()
                .transactionHistoryGraph(graphs)
                .build();
    }
}
