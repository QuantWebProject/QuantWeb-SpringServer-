package com.quantweb.springserver.domain.market.mapper;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketTagMapper {

    public Market_tag toMarketTag(Market_tag marketTag, Market market){
        return Market_tag.builder()
                .tag(marketTag.getTag())
                .market(market)
                .build();
    }

}
