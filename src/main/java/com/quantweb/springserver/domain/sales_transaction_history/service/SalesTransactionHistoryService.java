package com.quantweb.springserver.domain.sales_transaction_history.service;

import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.domain.sales_transaction_history.converter.SalesTransactionHistoryConverter;
import com.quantweb.springserver.domain.sales_transaction_history.dto.response.SalesHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import com.quantweb.springserver.domain.sales_transaction_history.repository.SalesTransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SalesTransactionHistoryService {

    private final SalesTransactionHistoryRepository salesTransactionHistoryRepository;
    private final BackTestRepository backTestRepository;

    public SalesHistoryResponseDto getSalesTransactionHistory(Long backtestId){

        SalesTransactionHistory findHistory = salesTransactionHistoryRepository.findByBackTestId(backtestId).orElseThrow();

        return SalesTransactionHistoryConverter.toHistoryResponseDto(findHistory);
    }

}
