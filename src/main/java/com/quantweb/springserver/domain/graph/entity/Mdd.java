package com.quantweb.springserver.domain.graph.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "mdd")
@AllArgsConstructor
@NoArgsConstructor
public class Mdd extends BaseTimeEntity {

    @Id
    @Column(name = "mdd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Float mdd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    private Graph graph;
}