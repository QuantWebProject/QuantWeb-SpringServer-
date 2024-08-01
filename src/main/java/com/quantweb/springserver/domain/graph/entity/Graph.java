package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.stock.entity.Stock;
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

    private GraphType type;

    @OneToMany(mappedBy = "graph", fetch = FetchType.LAZY)
    private List<MyStrategyGraph> myStrategyGraphs;

    @OneToOne(mappedBy = "graph", fetch = FetchType.LAZY)
    private Stock stock;
}