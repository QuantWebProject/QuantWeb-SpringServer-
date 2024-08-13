package com.quantweb.springserver.domain.market.respository;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_like;
import com.quantweb.springserver.domain.market.entity.Market_subscribe;
import com.quantweb.springserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketSubscribeRepository extends JpaRepository <Market_subscribe, Long> {
    Optional<Market_subscribe> findByUserAndMarket(User user, Market market);

}
