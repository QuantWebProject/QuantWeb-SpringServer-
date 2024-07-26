package com.quantweb.springserver.domain.history.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "day_history")
@AllArgsConstructor
@NoArgsConstructor
public class DayHistory extends BaseTimeEntity {

    @Id
    @Column(name = "day_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private History history;
}