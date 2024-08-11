package com.quantweb.springserver.domain.investment_sectors_pie_chart.repository;

import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PieChartRepository extends JpaRepository<InvestmentSectorsPieChart, Long> {

    Optional<List<InvestmentSectorsPieChart>> findAllByBackTestId(Long backtestId);
}
