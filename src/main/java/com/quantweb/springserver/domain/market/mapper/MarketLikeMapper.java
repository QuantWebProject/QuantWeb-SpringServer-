package com.quantweb.springserver.domain.market.mapper;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_like;
import com.quantweb.springserver.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MarketLikeMapper {

    public Market_like toMarketLike(User user, Market market){
        return Market_like.builder()
                .market(market)
                .user(user)
                .build();
    }
}
