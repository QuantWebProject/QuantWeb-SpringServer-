package com.quantweb.springserver.dashboard.service;

import com.quantweb.springserver.dashboard.controller.response.MyBackInvestResponse;
import com.quantweb.springserver.dashboard.controller.response.MyBackTestResponse;
import com.quantweb.springserver.dashboard.controller.response.RecommendInvestResponse;
import com.quantweb.springserver.dashboard.controller.response.SubscribeInvestResponse;

public class DashBoardService {

  BackTestRepository backTestRepository;
  InvestmentRepository investmentRepository;

  public MyBackTestResponse findMyBackTest(Long id) {
    BackTest backTest = backTestRepository.findByMemberId(id);

    return new MyBackTestResponse(backTest);
  }

  public MyBackInvestResponse findMyInvestment(Long id) {
    Investment backInvestment = investmentRepository.findByMemberId(id);

    return new MyBackInvestResponse(backInvestment);
  }

  public RecommendInvestResponse findRecommendInvestment(Long id) {
    Investment recommendInvestment = investmentRepository.sortByGain(id);

    return new RecommendInvestResponse(recommendInvestment);
  }

  public SubscribeInvestResponse findSubscribeInvestment(Long id) {
    Investment subscribeInvestment = investmentRepository.findByMemberId(id);

    return new SubscribeInvestResponse(subscribeInvestment);
  }
}
