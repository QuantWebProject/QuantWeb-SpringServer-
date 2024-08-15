package com.quantweb.springserver.domain.market.respository;

import com.quantweb.springserver.domain.market.entity.Market_tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketTagRepository extends JpaRepository<Market_tag, Long> {
}
