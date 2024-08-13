package com.quantweb.springserver.domain.market.service;

import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.dto.request.MarketUpdateRequest;
import com.quantweb.springserver.domain.market.dto.response.MarketPagingResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketSummaryResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketIdResponse;
import com.quantweb.springserver.domain.market.entity.Market;
import org.springframework.data.domain.Page;

public interface MarketService {
    MarketIdResponse createMarket(MarketCreateRequest request); //마켓 전략 생성
    MarketIdResponse updateMarket(Long marketId, MarketUpdateRequest request); // 마켓 전략 수정
    MarketIdResponse deleteMarket(Long marketId); // 마켓 전략 삭제
    //마켓 전략 백 테스트 최신순 조회
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketBackTestByCreatedAt(int page, int size);
    //마켓 전략 모의 투자 최신순 조회
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketInvestmentSimulationByCreatedAt(int page, int size);
    //마켓 전략 백 테스트 추천순 조회
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketBackTestByRecommend(int page, int size);
    //마켓 전략 모의 투자 추천순 조회
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketInvestmentSimulationByRecommend(int page, int size);
    // 마켓 전략 등록 되어 있는 백 테스트 검색
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketBackTestByKeyword(String keyword, int page, int size);
    // 마켓 전략 등록 되어 있는 모의 투자 검색
    MarketPagingResponse <MarketSummaryResponse> inquiryMarketInvestmentSimulationByKeyword(String keyword, int page, int size);
    // 마켓 좋아요 누르기
    Boolean likeMarket(Long marketId, Long userId);
    //마켓 구독 누르기
    Boolean subscribeMarket(Long marketId, Long userId);
}
