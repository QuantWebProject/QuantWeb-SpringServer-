package com.quantweb.springserver.domain.sales_transaction_history.repository;

import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<SalesTransactionHistory,Long> {
}
