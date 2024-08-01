package com.quantweb.springserver.domain.history.entity;

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
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
public class History extends BaseTimeEntity {

    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_test_id")
    private BackTest backTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_simulation_id")
    private InvestmentSimulation investmentSimulation;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<DayHistory> dayHistories;
}