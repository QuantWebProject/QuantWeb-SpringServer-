package com.quantweb.springserver.domain.sales_transaction_history.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springdoc.webmvc.core.fn.SpringdocRouteBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "sales_transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class SalesTransactionHistory extends BaseTimeEntity {

    @Id
    @Column(name = "sales_transaction_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_test_id")
    private BackTest backTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_simulation_id")
    private InvestmentSimulation investmentSimulation;

    private LocalDate dateTime;

    private Long quantity;

    private String ticker;

    private Float price;

    private Float fees;

    private Float profit;

    private Float realizedProfit;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
