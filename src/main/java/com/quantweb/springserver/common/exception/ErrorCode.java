package com.quantweb.springserver.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements CustomErrorCode{
    //User
    USER_NOT_FOUND("USER401", HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),

    //MARKET
    MARKET_NOT_FOUND("MARKET401", HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."),

    //BACKTEST
    BACKTEST_NOT_FOUND("BACKTEST401", HttpStatus.NOT_FOUND,"백테스트를 찾을 수 없습니다."),

    //INVESTMENT
    INVESTMENTSIMULATION_NOT_FOUND("INVESTMENTSIMULATION401", HttpStatus.NOT_FOUND,"모의투자 를 찾을 수 없습니다.");

    private final String name;
    private final HttpStatus httpStatus;
    private final String message;
}
