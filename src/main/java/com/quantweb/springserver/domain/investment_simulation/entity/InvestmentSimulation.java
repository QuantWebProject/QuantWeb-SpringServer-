package com.quantweb.springserver.domain.investment_simulation.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.TechnicalStrategy;
import com.quantweb.springserver.domain.graph.entity.MyStrategyGraph;
import com.quantweb.springserver.domain.history.entity.History;
import com.quantweb.springserver.domain.sales_transaction_history.entity.SalesTransactionHistory;
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
    private String name; // 투자 시뮬레이션 이름

    private Integer stockNum; // 종목 개수

    private Integer initInvestmentFund; // 투자 원금

//    private Integer fees;

    private Integer rebalancePeriod; // 리벨런싱 주기

//    @NotNull
//    private LocalDateTime investStartDate;
//
//    @NotNull
//    private LocalDateTime investEndDate;

    private Float yearlyAverageProfit; // 연평균 수익률 = 최종수익률/투자기간

    private Float realizedProfit; // 실현수익 = 매도수익 - 매수수수료

    private Float evaluatedProfitLoss; // 평가손익 = 현재가격 - 매수가격

    private Integer currentInvestmentFund; // 현재 투자금액 = 매수가격 * 보유수량

    private Boolean marketShared; // 투자비중

    private TechnicalStrategy strategy;

    @OneToOne(mappedBy = "investmentSimulation", fetch = FetchType.LAZY)
    private TechAnalyStrategyVariables variables;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<ValueInvestingStrategy> valueInvestingStrategy;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<History> history;

    @OneToMany(mappedBy = "investmentSimulation", cascade = CascadeType.ALL)
    private List<SalesTransactionHistory> salesTransactionHistories;

    @OneToOne(mappedBy = "investmentSimulation", fetch = FetchType.LAZY)
    private MyStrategyGraph myStrategyGraph;
}