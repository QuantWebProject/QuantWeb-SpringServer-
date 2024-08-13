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
    //마켓 백 테스트 조회(최신순)
    @Query("SELECT m FROM Market m Join m.backTest b WHERE b.investStartDate >= :currentDate  ORDER BY m.createdAt DESC")
    Page<Market> findAllBackTestByCreatedAt(Pageable pageable);
    //마켓 모의 투자 조회(최신순)
    @Query("SELECT m FROM Market m Join m.investmentSimulation i WHERE i.investStartDate >= :currentDate  ORDER BY m.subscribeNum DESC")
    Page<Market> findAllInvestmentSimulationByCreatedAt(Pageable pageable);
    //마켓 백 테스트 조회(추천순)
    @Query("SELECT m FROM Market m Join m.backTest b WHERE b.investStartDate >= :currentDate  ORDER BY m.subscribeNum DESC")
    Page<Market> findAllBackTestByRecommend(Pageable pageable);
    //마켓 모의 투자 조회(추천순)
    @Query("SELECT m FROM Market m Join m.investmentSimulation i WHERE i.investStartDate >= :currentDate  ORDER BY m.subscribeNum DESC")
    Page<Market> findAllInvestmentSimulationByRecommend(Pageable pageable);
    //마켓 백 테스트 전략 검색
    @Query("SELECT DISTINCT m FROM Market m JOIN m.marketTags t WHERE M.name LIKE%:keyword% OR t.tag LIKE%:keyword% AND m.investmentSimulation IS NULL")
    Page<Market> findAllBackTestBySearch(@Param("keyword") String keyword, Pageable pageable);

    //마켓 모의 투자 검색
    @Query("SELECT DISTINCT m FROM Market m JOIN m.marketTags t WHERE M.name LIKE%:keyword% OR t.tag LIKE%:keyword% AND m.backTest IS NULL")
    Page<Market> findAllInvestmentSimulationBySearch(@Param("keyword") String keyword, Pageable pageable);
}
