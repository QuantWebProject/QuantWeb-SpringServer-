package com.quantweb.springserver.domain.stock.repository;

import com.quantweb.springserver.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<List<Stock>> findAllByBackTestId(Long backtestId);
}
