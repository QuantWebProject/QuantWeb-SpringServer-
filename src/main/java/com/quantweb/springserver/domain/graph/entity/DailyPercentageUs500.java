package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "daily_percentage_us500")
@AllArgsConstructor
@NoArgsConstructor
public class DailyPercentageUs500 extends BaseTimeEntity {

    @Id
    @Column(name = "daily_percentage_us500_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Float returns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;
}