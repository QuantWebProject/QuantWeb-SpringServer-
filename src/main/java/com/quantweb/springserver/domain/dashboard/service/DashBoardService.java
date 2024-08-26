package com.quantweb.springserver.domain.dashboard.service;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.domain.dashboard.dto.BackTestSubscribeResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import com.quantweb.springserver.domain.market.entity.Market;
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
  private final BackTestRepository backTestRepository;
  private final MarketRepository marketRepository;

  public BackTestSubscribeResponse getRecommendationBackTest(Long userId,String type) {
    List<Market> marketList = marketSubscribeRepository.findAllByUserId(userId);
    BackTestSubscribeResponse backTestSubscribeResponse = new BackTestSubscribeResponse(marketList);
    sortMarketByType(backTestSubscribeResponse,type);
    return backTestSubscribeResponse;
  }

  public MyBackTestResponse getMyBackTest(Long userId, String type) {
    List<BackTest> backTests = backTestRepository.findAllByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저의 백테스트가 존재하지 않습니다."));
    MyBackTestResponse myBackTestResponse = new MyBackTestResponse(backTests,marketRepository);
    sortBackTestBySubscriptionStatusAndType(myBackTestResponse,type);
    return myBackTestResponse;
  }

  private void sortBackTestBySubscriptionStatusAndType(MyBackTestResponse backTestSubscribeResponse,String type) {
    backTestSubscribeResponse.getMarketResponses().sort((o1, o2) -> {
      // 구독 중인 상태를 먼저 정렬
      int subscribeCompare = Boolean.compare(o2.getMarketShared(), o1.getMarketShared());
      if (subscribeCompare != 0) {
        return subscribeCompare;
      }

      // 구독 상태가 같으면, type에 따른 정렬
      if ("profit".equals(type)) {
        return o2.getYearlyProfit().compareTo(o1.getYearlyProfit());
      } else if ("latest".equals(type)) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
      }

      return 0; // 기본적으로 같다면 순서를 유지
    });
  }

  private void sortMarketByType(BackTestSubscribeResponse backTestSubscribeResponse, String type) {
      switch (type) {
          case "profit" -> backTestSubscribeResponse.getMarketResponses()
                  .sort((o1, o2) -> o2.getYearlyProfit().compareTo(o1.getYearlyProfit()));
          case "latest" -> backTestSubscribeResponse.getMarketResponses()
                  .sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
          case "subscribe" -> backTestSubscribeResponse.getMarketResponses()
                  .sort((o1, o2) -> o2.getSubscribeNum().compareTo(o1.getSubscribeNum()));
      }
  }
}