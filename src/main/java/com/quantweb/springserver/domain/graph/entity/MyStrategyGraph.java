package com.quantweb.springserver.domain.graph.entity;

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
@Table(name = "my_strategy_graph")
@AllArgsConstructor
@NoArgsConstructor
public class MyStrategyGraph extends BaseTimeEntity {

    @Id
    @Column(name = "my_strategy_graph_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private BackTest backTest;

    @OneToOne(fetch = FetchType.LAZY)
    private InvestmentSimulation investmentSimulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;
}