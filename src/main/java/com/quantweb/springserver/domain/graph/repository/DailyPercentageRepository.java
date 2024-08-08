package com.quantweb.springserver.domain.graph.repository;

import com.quantweb.springserver.domain.graph.entity.DailyPercentage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPercentageRepository extends JpaRepository<DailyPercentage, Long> {

}
