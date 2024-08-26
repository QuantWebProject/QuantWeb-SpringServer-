package com.quantweb.springserver.domain.dashboard.service;

import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.domain.dashboard.dto.BackTestSubscribeResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestRecommendationResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.respository.MarketRepository;
import com.quantweb.springserver.domain.market.respository.MarketSubscribeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashBoardService {

  private final MarketSubscribeRepository marketSubscribeRepository;
  private final BackTestRepository backTestRepository;

  public Page<MyBackTestRecommendationResponse> getRecommendationBackTest(Pageable pageable, Long userId) {
    return backTestRepository.getAllBackTestSortByRecommendation(pageable,userId);
  }

  public Page<MyBackTestResponse> getMyBackTest(Pageable pageable, Long userId, String type) {
    if(type.equals("subscribe")) {
      return backTestRepository.getAllBackTestSortBySubscribe(pageable, userId);
    }
     return backTestRepository.getAllBackTestSortByLatest(pageable, userId,type);
  }

}