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
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.Market_tag;
import com.quantweb.springserver.domain.market.mapper.MarketMapper;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;
    private final MarketMapper marketMapper;
    private final BackTestRepository backTestRepository;
    private final InvestmentSimulationRepository investmentSimulationRepository;
    private final MarketTagService marketTagService;
    /*
     * 마켓 전략 생성
     */
    @Override
    @Transactional
    public MarketIdResponse createMarket(MarketCreateRequest request){
        BackTest backTest = null;
        InvestmentSimulation investmentSimulation = null;
        if(request.getBackTestId() != null) {
            backTest = backTestRepository.findById(request.getBackTestId())
                    .orElseThrow(() -> new CustomException(ErrorCode.BACKTEST_NOT_FOUND));
        }
        if(request.getInvestmentSimulationId() != null) {
            investmentSimulation = investmentSimulationRepository.findById(request.getInvestmentSimulationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));
        }

        Market newMarket = createAndSaveMarket(request, backTest, investmentSimulation);

        List<Market_tag> marketTags = request.getMarketTags().stream()
                .map(tag -> marketTagService.createMarketTags(tag, newMarket))
                .collect(Collectors.toList());

        newMarket.getMarketTags().addAll(marketTags);

        return new MarketIdResponse(newMarket.getId());
    }
    private Market createAndSaveMarket(MarketCreateRequest request, BackTest backTest, InvestmentSimulation investmentSimulation){
        Market market = marketMapper.toMarket(request,backTest, investmentSimulation);
        return marketRepository.save(market);
    }


    /*
     * 마켓 전략 수정
     */
    @Override
    @Transactional
    public MarketIdResponse updateMarket(Long marketId, MarketUpdateRequest request){
        /*
            본인이 아닌 경우 수정 불가능하게 유효성 검사 추가
         */
        Market market = marketRepository.findById(marketId).orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));

        BackTest backTest = null;
        InvestmentSimulation investmentSimulation = null;

        if(request.getBackTestId() != null) {
            backTest = backTestRepository.findById(request.getBackTestId())
                    .orElseThrow(() -> new CustomException(ErrorCode.BACKTEST_NOT_FOUND));
        }
        if(request.getInvestmentSimulationId() != null) {
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

    @Override
    @Transactional
    public MarketIdResponse deleteMarket(Long marketId){
        /*
            본인이 아닌 경우 삭제 불가능하게 유효성 검사 추가
         */
        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new CustomException(ErrorCode.MARKET_NOT_FOUND));

        Long deletedMarketId = market.getId();
        marketRepository.deleteById(deletedMarketId);

        return new MarketIdResponse(deletedMarketId);
    }
}
