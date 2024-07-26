package com.quantweb.springserver.domain.tech_analy_strategy_variables.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "tech_analy_strategy_variables")
@AllArgsConstructor
@NoArgsConstructor
public class TechAnalyStrategyVariables extends BaseTimeEntity {

    @Id
    @Column(name = "tech_analy_strategy_variables_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_test_id")
    private BackTest backTest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_simulation_id")
    private InvestmentSimulation investmentSimulation;

    private Integer topNAssets;

    private Integer momentumObservationPeriod;

    private Integer riskObservationPeriod;

    private Integer longTermParameter;

    private Integer kDaysParameter;

    private Integer movingAverageRefDays;

    private Integer rsiObservationPeriod;

    private Integer StandardDeviation;

    private Integer movingAverageCalPeriod;
}