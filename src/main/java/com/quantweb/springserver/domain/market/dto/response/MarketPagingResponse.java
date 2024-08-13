package com.quantweb.springserver.domain.market.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class MarketPagingResponse<T> {
    private List<T> backTests;
    private List<T> investmentSimulation;
    private int page;
    private int totalPages;
    private int totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}
