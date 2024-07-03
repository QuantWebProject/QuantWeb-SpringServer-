package com.quantweb.springserver.dashboard.domain;



public class DashBoard {
  Long id;

  Members member;

  BackTest backtest;

  List<Invest> myInvest;

  List<Invest> recommendInvest;

  List<Invest> subscribeInvest;
}
