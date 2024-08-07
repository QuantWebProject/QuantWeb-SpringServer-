package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "graph")
@AllArgsConstructor
@NoArgsConstructor
public class Graph extends BaseTimeEntity {

    @Id
    @Column(name = "graph_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_test_id")
    private BackTest backTest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_simulation_id")
    private InvestmentSimulation investmentSimulation;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
    private List<DailyPercentageUs500> dailyPercentageUs500s;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
    private List<DailyPercentage> dailyPercentages;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
    private List<MddUs500> mddUs500s;

    @OneToMany(mappedBy = "graph", cascade = CascadeType.ALL)
    private List<Mdd> mdds;

}