package com.quantweb.springserver.domain.graph.service;

import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.graph.converter.GraphConverter;
import com.quantweb.springserver.domain.graph.entity.Graph;
import com.quantweb.springserver.domain.graph.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GraphService {

    private final GraphRepository graphRepository;
    private final DailyPercentageRepository dailyPercentageRepository;
    private final DailyPercentageUs500Repository dailyPercentageUs500Repository;
    private final MddRepository mddRepository;
    private final MddUs500Repository mddUs500Repository;

    @Transactional
    public void saveGraph(BackTest backTest, Graph graph){

        graphRepository.save(graph);
    }

    @Transactional
    public void saveDailyPercentage(ArrayList<InvestmentResultDto.GraphItem> backtest, Graph graph){

        backtest.forEach(bt -> {
            dailyPercentageRepository.save(GraphConverter.toDailyPercentage(bt,graph));
        });
    }
    @Transactional
    public void saveDailyPercentageUs500(ArrayList<InvestmentResultDto.GraphItem> us500, Graph graph){

        us500.forEach(bt -> {
            dailyPercentageUs500Repository.save(GraphConverter.toDailyPercentageUs500(bt,graph));
        });
    }
    @Transactional
    public void saveMdd(ArrayList<InvestmentResultDto.GraphItem> backtest, Graph graph){

        backtest.forEach(bt -> {
            mddRepository.save(GraphConverter.toMdd(bt,graph));
        });
    }
    @Transactional
    public void saveMddUs500(ArrayList<InvestmentResultDto.GraphItem> us500, Graph graph){

        us500.forEach(bt -> {
            mddUs500Repository.save(GraphConverter.toMddUs500(bt,graph));
        });
    }
}
