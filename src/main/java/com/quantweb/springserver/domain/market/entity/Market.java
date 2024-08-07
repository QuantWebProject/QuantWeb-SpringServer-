package com.quantweb.springserver.domain.market.entity;

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
@Table(name = "Market")
@AllArgsConstructor
@NoArgsConstructor
public class Market extends BaseTimeEntity {

    @Id
    @Column(name = "market_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private BackTest backTest;

    @OneToOne(fetch = FetchType.LAZY)
    private InvestmentSimulation investmentSimulation;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Market_like> marketLikes;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Market_subscribe> marketSubscribes;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Market_tag> marketTags;
}