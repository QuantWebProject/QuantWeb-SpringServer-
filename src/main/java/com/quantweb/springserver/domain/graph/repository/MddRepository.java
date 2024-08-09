package com.quantweb.springserver.domain.graph.repository;

import com.quantweb.springserver.domain.graph.entity.Mdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MddRepository extends JpaRepository<Mdd, Long> {

    List<Mdd> findAllByGraphId(Long graphId);
}
