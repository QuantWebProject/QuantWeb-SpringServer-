package com.quantweb.springserver.domain.tech_analy_strategy_variables.entity;

import com.quantweb.springserver.domain.user.user_status.UserStatus;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum StrategyType {
    PBR("pbr"),
    PER("per"),
    PSR("psr"),
    PCF("p/cf"),
    MARKET_CAP("market_cap"),
    PROFITABILITY_PERCENTAGE("profitability_percentage"),
    GROSS_MARGIN("gross_margin"),
    OPERATING_MARGIN("operating_margin"),
    NET_PROFIT_MARGIN("net_profit_margin"),
    ROE("roe"),
    ROA("roa");

    private final String factor;

    StrategyType(String factor) {
        this.factor = factor;
    }

    public static StrategyType fromFactor(String factor) {
        return Arrays.stream(StrategyType.values())
                .filter(e -> e.getFactor().equals(factor))
                .findAny()
                .orElse(null);
    }
}
