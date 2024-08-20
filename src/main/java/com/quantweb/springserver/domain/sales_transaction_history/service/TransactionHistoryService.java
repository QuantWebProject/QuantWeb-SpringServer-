package com.quantweb.springserver.domain.sales_transaction_history.service;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.sales_transaction_history.converter.TransactionHistoryConverter;
import com.quantweb.springserver.domain.sales_transaction_history.dto.response.TransactionHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import com.quantweb.springserver.domain.sales_transaction_history.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    public void saveTransactionHistory(ArrayList<InvestmentResultDto.TransactionHistoryGraphItem> transactionHistory, BackTest backTest){

        transactionHistory.forEach(th -> {
            transactionHistoryRepository.save(TransactionHistoryConverter.toTransactionHistory(th, backTest));
        });
    }

    public TransactionHistoryResponseDto getTransactionHistory(Long backtestId){

        List<SalesTransactionHistory> transactionHistories = transactionHistoryRepository.findAllByBackTestId(backtestId).orElseThrow(() -> new RuntimeException("백테스트가 존재하지 않습니다."));

        return TransactionHistoryConverter.toTransactionHistoryResultDto(transactionHistories);
    }
}
