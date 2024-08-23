package com.quantweb.springserver.domain.dashboard.service;

import com.quantweb.springserver.domain.dashboard.dto.MarketMyResponse;
import com.quantweb.springserver.domain.dashboard.dto.MarketSubscribeResponse;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.respository.MarketLikeRepository;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import com.quantweb.springserver.domain.market.respository.MarketSubscribeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashBoardService {

  private final MarketSubscribeRepository marketSubscribeRepository;
  private final MarketRepository marketRepository;

  public  MarketSubscribeResponse getMySubscribe(Long userId) {
    List<Market> marketList = marketSubscribeRepository.findAllByUserId(userId);
    return new MarketSubscribeResponse(marketList);
  }

  public MarketMyResponse getMyBackTest(Long userId) {
    List<Market> marketList = marketRepository.findAllBackTestByUserId(userId);
    return new MarketMyResponse(marketList);
  }
}
