package com.quantweb.springserver.domain.market.service;

import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.dto.request.MarketUpdateRequest;
import com.quantweb.springserver.domain.market.dto.response.MarketIdResponse;

public interface MarketService {
    MarketIdResponse createMarket(MarketCreateRequest request); //마켓 전략 생성
    MarketIdResponse updateMarket(Long marketId, MarketUpdateRequest request); // 마켓 전략 수정
    MarketIdResponse deleteMarket(Long marketId); // 마켓 전략 삭제
    //InquiryMarketResponse //마켓 전략 조회



}
