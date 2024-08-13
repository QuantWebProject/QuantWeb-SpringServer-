package com.quantweb.springserver.domain.market.respository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.market.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MarketRepository extends JpaRepository<Market, Long> {
    @Query("SELECT DISTINCT m FROM Market m JOIN m.marketTags t WHERE M.name LIKE%:keyword% OR t.tag LIKE%:keyword% AND m.investmentSimulation IS NULL")
    Page<Market> findAllBackTestBySearch(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT DISTINCT m FROM Market m JOIN m.marketTags t WHERE M.name LIKE%:keyword% OR t.tag LIKE%:keyword% AND m.backTest IS NULL")
    Page<Market> findAllInvestmentSimulationBySearch(@Param("keyword") String keyword, Pageable pageable);
}
