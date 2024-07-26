package com.quantweb.springserver.domain.market.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "Market_tag")
@AllArgsConstructor
@NoArgsConstructor
public class Market_tag extends BaseTimeEntity {

    @Id
    @Column(name = "market_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market")
    private Market market;
}