package com.quantweb.springserver.domain.sales_transaction_history.repository;

import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesTransactionHistoryRepository extends JpaRepository<SalesTransactionHistory, Long> {

    Optional<SalesTransactionHistory> findByBackTestId(Long backTestId);
}
