package com.quantweb.springserver.domain.graph.repository;

import com.quantweb.springserver.domain.graph.entity.MddUs500;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MddUs500Repository extends JpaRepository<MddUs500, Long> {

    List<MddUs500> findAllByGraphId(Long graphId);
}
