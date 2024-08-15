package com.quantweb.springserver.domain.market.service;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_tag;

import java.util.List;

public interface MarketTagService {
    Market_tag createMarketTags(Market_tag marketTag, Market market);
}
