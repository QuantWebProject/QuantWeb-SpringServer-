package com.quantweb.springserver.domain.sales_transaction_history.repository;

import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<SalesTransactionHistory,Long> {

    Optional<List<SalesTransactionHistory>> findAllByBackTestId(Long backtestId);
}
