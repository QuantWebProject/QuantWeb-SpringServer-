package com.quantweb.springserver.domain.market.entity;

import com.quantweb.springserver.common.entity.BaseTimeEntity;
import com.quantweb.springserver.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "Market_like")
@AllArgsConstructor
@NoArgsConstructor
public class Market_like extends BaseTimeEntity {

    @Id
    @Column(name = "market_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isChecked; // 좋아요 상태 추가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market")
    private Market market;

    public boolean changeIsChecked(){
        this.isChecked = !this.isChecked;
        return isChecked;
    }
}