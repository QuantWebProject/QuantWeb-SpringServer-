package com.quantweb.springserver.domain.sales_transaction_history.service;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.sales_transaction_history.converter.TransactionHistoryConverter;
import com.quantweb.springserver.domain.sales_transaction_history.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
}
