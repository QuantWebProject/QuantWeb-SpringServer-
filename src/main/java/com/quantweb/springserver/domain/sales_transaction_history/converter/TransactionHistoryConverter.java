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
                .price(transactionHistory.getTotal_amount())
                .fees(transactionHistory.getFee())
                .profit(transactionHistory.getProfit())
                .realizedProfit(transactionHistory.getRealized_profit())
                .build();
    }

    public static TransactionHistoryResponseDto.TransactionHistory toBuyTransactionHistory(SalesTransactionHistory transactionHistories){

        return TransactionHistoryResponseDto.TransactionHistory.builder()
                .transactionDate(transactionHistories.getDateTime())
                .stockName(transactionHistories.getTicker())
                .realizedProfit(transactionHistories.getRealizedProfit())
                .profit(transactionHistories.getProfit())
                .buyQuantity(Float.valueOf(transactionHistories.getQuantity()))
                .buyPrice((long) Float.floatToIntBits(transactionHistories.getPrice()))
                .sellQuantity(null)
                .sellPrice(null)
                .fee(transactionHistories.getFees())
                .build();
    }

    public static TransactionHistoryResponseDto.TransactionHistory toSellTransactionHistory(SalesTransactionHistory transactionHistories){

        return TransactionHistoryResponseDto.TransactionHistory.builder()
                .transactionDate(transactionHistories.getDateTime())
                .stockName(transactionHistories.getTicker())
                .realizedProfit(transactionHistories.getRealizedProfit())
                .profit(transactionHistories.getProfit())
                .buyQuantity(null)
                .buyPrice(null)
                .sellQuantity(Float.valueOf(transactionHistories.getQuantity()))
                .sellPrice((long) Float.floatToIntBits(transactionHistories.getPrice()))
                .fee(transactionHistories.getFees())
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

        List<TransactionHistoryResponseDto.TransactionHistory> histories = transactionHistories.stream()
                .map(th->{
                    if (th.getType().equals(TransactionType.buy)){
                        return TransactionHistoryConverter.toBuyTransactionHistory(th);
                    } else {
                        return TransactionHistoryConverter.toSellTransactionHistory(th);
                    }
                })
                .collect(Collectors.toList());


        List<TransactionHistoryResponseDto.TransactionHistoryGraph> graphs = transactionHistories.stream()
                .map(TransactionHistoryConverter::toTransactionHistoryGraph)
                .toList();

        return TransactionHistoryResponseDto.builder()
                .transactionHistories(histories)
                .transactionHistoryGraph(graphs)
                .build();
    }
}
