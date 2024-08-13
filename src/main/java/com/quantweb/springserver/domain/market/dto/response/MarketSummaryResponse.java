package com.quantweb.springserver.domain.market.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class MarketSummaryResponse {
    private String name;//제목
    private String nickName; //닉네임
    private LocalDate startDate;  //투자 시작 시간
    private LocalDate endDate;  //투자 끝 시간
    private Float finalCumulativeReturn;  //누적 수익률
    private Float mdd; // mdd
    private Integer subscribeNum; //구독수
    private Boolean subscribeStatus; //구독 여부
}
