package com.quantweb.springserver.domain.graph.repository;

import com.quantweb.springserver.domain.graph.entity.DailyPercentageUs500;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyPercentageUs500Repository extends JpaRepository<DailyPercentageUs500, Long> {

    List<DailyPercentageUs500> findAllByGraphId(Long graphId);
}
