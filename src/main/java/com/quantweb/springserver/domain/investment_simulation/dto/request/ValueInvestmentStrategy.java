package com.quantweb.springserver.domain.investment_simulation.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValueInvestmentStrategy {

    private List<Option> options;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Option {
        private String factor;
        private List<Integer> percent;
        private List<Float> range;
    }
}
