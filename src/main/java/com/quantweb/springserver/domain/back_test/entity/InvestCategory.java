package com.quantweb.springserver.domain.back_test.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "InvestCategory")
@AllArgsConstructor
@NoArgsConstructor
public class InvestCategory extends BaseTimeEntity {

    @Id
    @Column(name = "back_test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




}
