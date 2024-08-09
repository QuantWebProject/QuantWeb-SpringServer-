package com.quantweb.springserver.domain.graph.repository;

import com.quantweb.springserver.domain.graph.entity.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Long> {

    Graph findByBackTestId(Long backtestid);
}
