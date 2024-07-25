package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "Stock")
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends BaseTimeEntity {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EmbeddedId
    private PriceChangeAndMddFK priceChangeAndMddFK;

    private String stockName;
}