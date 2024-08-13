package com.quantweb.springserver.domain.market.service;

import com.quantweb.springserver.common.exception.CustomException;
import com.quantweb.springserver.common.exception.ErrorCode;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.investment_simulation.repository.InvestmentSimulationRepository;
import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.dto.request.MarketUpdateRequest;
import com.quantweb.springserver.domain.market.dto.response.MarketIdResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketPagingResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketSummaryResponse;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_like;
import com.quantweb.springserver.domain.market.entity.Market_subscribe;
import com.quantweb.springserver.domain.market.entity.Market_tag;
import com.quantweb.springserver.domain.market.mapper.MarketLikeMapper;
import com.quantweb.springserver.domain.market.mapper.MarketMapper;
import com.quantweb.springserver.domain.market.mapper.MarketSubscribeMapper;
import com.quantweb.springserver.domain.market.respository.MarketLikeRepository;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import com.quantweb.springserver.domain.market.respository.MarketSubscribeRepository;
import com.quantweb.springserver.domain.user.entity.User;
import com.quantweb.springserver.domain.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;
    private final MarketLikeRepository marketLikeRepository;
    private final MarketSubscribeRepository marketSubscribeRepository;
    private final MarketSubscribeMapper marketSubscribeMapper;
    private final UserRepository userRepository;
    private final MarketMapper marketMapper;
    private final BackTestRepository backTestRepository;
    private final InvestmentSimulationRepository investmentSimulationRepository;
    private final MarketTagService marketTagService;
    private final MarketLikeMapper marketLikeMapper;

    /*
     * 마켓 전략 생성
     */
    @Override
    @Transactional
    public MarketIdResponse createMarket(MarketCreateRequest request) {
        BackTest backTest = null;
        InvestmentSimulation investmentSimulation = null;
        if (request.getBackTestId() != null) {
            backTest = backTestRepository.findById(request.getBackTestId())
                    .orElseThrow(() -> new CustomException(ErrorCode.BACKTEST_NOT_FOUND));
            backTest.updateMarketShared();//마켓 공유 상태 true로 변경
        }
        if (request.getInvestmentSimulationId() != null) {
            investmentSimulation = investmentSimulationRepository.findById(request.getInvestmentSimulationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.INVESTMENTSIMULATION_NOT_FOUND));
            investmentSimulation.updateMarketShared(); //마켓 공유 상태 true로 변경
        }

        Market newMarket = createAndSaveMarket(request, backTest, investmentSimulation);

        List<Market_tag> marketTags = request.getMarketTags().stream()
                .map(tag -> marketTagService.createMarketTags(tag, newMarket))
                .collect(Collectors.toList());

        newMarket.getMarketTags().addAll(marketTags);

        return new MarketIdResponse(newMarket.getId());
    }

    private Market createAndSaveMarket(MarketCreateRequest request, BackTest backTest, InvestmentSimulation investmentSimulation) {
        Market market = marketMapper.toMarket(request, backTest, investmentSimulation);
        return marketRepository.save(market);
    }


    /*
     * 마켓 전략 수정
     */
    @Override
    @Transactional
    public MarketIdResponse updateMarket(Long marketId, MarketUpdateRequest request) {
        /*
            본인이 아닌 경우 수정 불가능하게 유효성 검사 추가
         */
        Market market = marketRepository.findById(marketId).orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));

        BackTest backTest = null;
        InvestmentSimulation investmentSimulation = null;

        if (request.getBackTestId() != null) {
            backTest = backTestRepository.findById(request.getBackTestId())
                    .orElseThrow(() -> new CustomException(ErrorCode.BACKTEST_NOT_FOUND));
        }
        if (request.getInvestmentSimulationId() != null) {
            investmentSimulation = investmentSimulationRepository.findById(request.getInvestmentSimulationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));
        }
        market.getMarketTags().clear();

        List<Market_tag> marketTags = request.getMarketTags().stream()
                .map(tag -> marketTagService.createMarketTags(tag, market))
                .collect(Collectors.toList());

        market.getMarketTags().addAll(marketTags);

        market.updateMarketInfo(request.getName(), backTest, investmentSimulation);

        return new MarketIdResponse(market.getId());
    }

    /*
     * 마켓 전략 삭제
     */
    @Override
    @Transactional
    public MarketIdResponse deleteMarket(Long marketId) {
        /*
            본인이 아닌 경우 삭제 불가능하게 유효성 검사 추가
         */
        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));

        Long deletedMarketId = market.getId();
        marketRepository.deleteById(deletedMarketId);

        return new MarketIdResponse(deletedMarketId);
    }
    /*
     * 마켓 백테스트 전략 조회(최신순)
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketBackTestByCreatedAt(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllBackTestByCreatedAt(pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketBackTestSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);
    }
    /*
     * 마켓 백테스트 전략 조회(최신순)
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketInvestmentSimulationByCreatedAt(Long userId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllInvestmentSimulationByCreatedAt(pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketInvestmentSimulationSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);
    }
    /*
     * 마켓 백테스트 전략 조회(추천순)
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketBackTestByRecommend(Long userId,int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllBackTestByRecommend(pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketBackTestSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);    }

    /*
     * 마켓 모의투자 전략 조회(추천순)
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketInvestmentSimulationByRecommend(Long userId,int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllInvestmentSimulationByRecommend(pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketInvestmentSimulationSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);
    }

    /*
     * 마켓 백테스트 전략 검색
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketBackTestByKeyword(Long userId,String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllBackTestBySearch(keyword, pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketBackTestSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);
    }

    /*
     * 마켓 모의투자 전략 검색
     */
    @Override
    @Transactional
    public MarketPagingResponse<MarketSummaryResponse> inquiryMarketInvestmentSimulationByKeyword(Long userId,String keyword, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Market> marketPage = marketRepository.findAllInvestmentSimulationBySearch(keyword, pageable);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Page<MarketSummaryResponse> marketSummaryPage = marketPage.map(market ->
                marketMapper.toMarketInvestmentSimulationSummaryResponse(market, user)
        );
        return marketMapper.toMarketPagingResponse(marketSummaryPage);
    }
    /*
     * 마켓 전략 좋아요 누르기
     */
    @Override
    @Transactional
    public Boolean likeMarket(Long marketId, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        Market market = marketRepository.findById(marketId).
                orElseThrow(()->new CustomException(ErrorCode.MARKET_NOT_FOUND));

        Optional<Market_like> marketLike = marketLikeRepository.findByUserAndMarket(user, market);

        return marketLike.map(Market_like::changeIsChecked)
                .orElseGet(() -> marketLikeRepository.save(marketLikeMapper.toMarketLike(user, market)).isChecked());
    }
    /*
     * 마켓 전략 좋아요 누르기
     */
    @Override
    @Transactional
    public Boolean subscribeMarket(Long marketId, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        Market market = marketRepository.findById(marketId).
                orElseThrow(()->new CustomException(ErrorCode.MARKET_NOT_FOUND));

        Optional<Market_subscribe> marketSubscribe = marketSubscribeRepository.findByUserAndMarket(user, market);
        return marketSubscribe.map(Market_subscribe::changeIsChecked)
                .orElseGet(()-> marketSubscribeRepository.save(marketSubscribeMapper.toMarketSubscribe(user, market)).isChecked());
    }
}