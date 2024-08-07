package com.quantweb.springserver.domain.investment_simulation.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.TechnicalStrategy;
import com.quantweb.springserver.domain.graph.entity.Graph;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
import com.quantweb.springserver.domain.stock.entity.Stock;
import com.quantweb.springserver.domain.user.entity.User;
import com.quantweb.springserver.domain.value_investing_strategy.entity.ValueInvestingStrategy;
import com.quantweb.springserver.domain.tech_analy_strategy_variables.entity.TechAnalyStrategyVariables;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "investment_simulation")
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentSimulation extends BaseTimeEntity {

    @Id
    @Column(name = "investment_simulation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String name;

    private Integer stockNum;

    private Integer initInvestmentFund;

    private Integer fees;

    private Integer rebalancePeriod;

    @NotNull
    private LocalDateTime investStartDate;

    @NotNull
    private LocalDateTime investEndDate;

    private Float yearlyAverageProfit;

    private Float realizedProfit;

    private Float evaluatedProfitLoss;

    private Integer finalAsset;

    private Boolean marketShared;

    private LocalDateTime deletedAt;

    private TechnicalStrategy strategy;

    @OneToOne(mappedBy = "investmentSimulation", fetch = FetchType.LAZY)
    private TechAnalyStrategyVariables variables;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<ValueInvestingStrategy> valueInvestingStrategy;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<Stock> stock;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<InvestmentSectorsPieChart> pieCharts;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<SalesTransactionHistory> salesTransactionHistories;

    @OneToOne(mappedBy = "investmentSimulation", fetch = FetchType.LAZY)
    private Graph graph;
}