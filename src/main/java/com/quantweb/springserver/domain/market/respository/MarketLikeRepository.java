package com.quantweb.springserver.domain.market.respository;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_like;
import com.quantweb.springserver.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface MarketLikeRepository extends JpaRepository <Market_like, Long> {

    Optional<Market_like> findByUserAndMarket(User user, Market market);
}
