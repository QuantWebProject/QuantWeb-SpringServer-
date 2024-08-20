package com.quantweb.springserver.domain.dashboard.service;

import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashBoardService {

  private final MarketRepository marketRepository;

  public  List<Market> getMyBackTest(Long userId) {
    List<Market> marketList = marketRepository.findAllByUserId(userId);
    return marketList;
  }
}
