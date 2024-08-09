package com.quantweb.springserver.domain.sales_transaction_history.converter;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import com.quantweb.springserver.domain.sales_transaction_history.entity.TransactionType;

import java.time.LocalDate;

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
}
