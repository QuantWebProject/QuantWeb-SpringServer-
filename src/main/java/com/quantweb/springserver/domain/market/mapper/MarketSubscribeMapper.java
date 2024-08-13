package com.quantweb.springserver.domain.market.mapper;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_subscribe;
import com.quantweb.springserver.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MarketSubscribeMapper {
    public Market_subscribe toMarketSubscribe(User user, Market market){
        return Market_subscribe.builder()
                .user(user)
                .market(market)
                .build();
    }
}
