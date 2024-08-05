package com.quantweb.springserver.domain.stock.repository;

import com.quantweb.springserver.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
