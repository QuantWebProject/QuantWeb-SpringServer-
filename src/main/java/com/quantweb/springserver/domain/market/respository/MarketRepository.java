package com.quantweb.springserver.domain.market.respository;

import com.quantweb.springserver.domain.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
