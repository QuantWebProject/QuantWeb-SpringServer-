package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "daily_percentage")
@AllArgsConstructor
@NoArgsConstructor
public class DailyPercentage extends BaseTimeEntity {

    @Id
    @Column(name = "daily_percentage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private Float returns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;
}