package com.quantweb.springserver.domain.back_test.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "backTest")
@AllArgsConstructor
@NoArgsConstructor
public class BackTest extends BaseTimeEntity {

    @Id
    @Column(name = "back_test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private Integer stockNum;

    private Long initInvestmentFund;

    private Float fees;

    private String rebalancePeriod;

    private LocalDate investStartDate;

    private LocalDate investEndDate;

    private Float yearlyAverageProfit;

    private Long realizedProfit;

    private Long unrealized_profit;

    private Long finalAsset;

    private Boolean marketShared;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    private TechnicalStrategy technicalStrategy;

    @OneToOne(mappedBy = "backTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TechAnalyStrategyVariables variables;

    @OneToMany(mappedBy = "backTest", cascade = CascadeType.ALL)
    private List<ValueInvestingStrategy> valueInvestingStrategy;

    @OneToMany(mappedBy = "backTest", cascade = CascadeType.ALL)
    private List<Stock> stock;

    @OneToMany(mappedBy = "backTest", cascade = CascadeType.ALL)
    private List<InvestmentSectorsPieChart> pieCharts;

    @OneToMany(mappedBy = "backTest", cascade = CascadeType.ALL)
    private List<SalesTransactionHistory> salesTransactionHistories;

    @OneToOne(mappedBy = "backTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Graph graph;

    public void updateMarketShared(){
        this.marketShared = !marketShared;
    }


}
