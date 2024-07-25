package com.quantweb.springserver.domain.daily_percentage.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.graph.entity.Graph;
import com.quantweb.springserver.domain.graph.entity.Stock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private Float profit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;

    @OneToMany(mappedBy = "daily_percentage", cascade = CascadeType.ALL)
    private List<DailyPercentage> dailyPercentages;

    @OneToOne(mappedBy = "graph", fetch = FetchType.LAZY)
    private Stock stock;
}