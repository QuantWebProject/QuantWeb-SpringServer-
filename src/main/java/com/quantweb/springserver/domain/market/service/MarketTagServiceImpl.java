package com.quantweb.springserver.domain.market.service;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_tag;
import com.quantweb.springserver.domain.market.mapper.MarketTagMapper;
import com.quantweb.springserver.domain.market.respository.MarketTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketTagServiceImpl implements  MarketTagService{
    private final MarketTagMapper marketTagMapper;
    private final MarketTagRepository marketTagRepository;

    /*
        마켓 태크 생성
     */
    @Override
    @Transactional
    public Market_tag createMarketTags(Market_tag marketTag, Market market){
        Market_tag newMarketTag = marketTagMapper.toMarketTag(marketTag, market);
        return marketTagRepository.save(newMarketTag);
    }
}
