package com.quantweb.springserver.domain.value_investing_strategy.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.tech_analy_strategy_variables.entity.StrategyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "value_investing_strategy")
@AllArgsConstructor
@NoArgsConstructor
public class ValueInvestingStrategy extends BaseTimeEntity {

    @Id
    @Column(name = "value_investing_strategy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_test_id")
    private BackTest backTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_simulation_id")
    private InvestmentSimulation investmentSimulation;

    private StrategyType type;

    private Float startValue;

    private Float endValue;
}