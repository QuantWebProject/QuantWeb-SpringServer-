package com.quantweb.springserver.domain.investment_simulation.repository;

import com.quantweb.springserver.domain.investment_simulation.entity.InvestmentSimulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentSimulationRepository extends JpaRepository<InvestmentSimulation, Long> {
}
