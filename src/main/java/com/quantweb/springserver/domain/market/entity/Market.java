package com.quantweb.springserver.domain.market.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import com.quantweb.springserver.domain.market.dto.request.MarketUpdateRequest;
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

    private Integer subscribeNum; //구독수 추가

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

    public void updateMarketInfo(String name, BackTest backTest, InvestmentSimulation investmentSimulation){
        this.name = name;
        this.backTest = backTest;
        this.investmentSimulation = investmentSimulation;
    }

    public void increaseSubscribeNum(){
        this.subscribeNum++;
    }
    public void decreaseSubscribeNum() {
        if (this.subscribeNum > 0) {
            this.subscribeNum--;
        }
    }
}