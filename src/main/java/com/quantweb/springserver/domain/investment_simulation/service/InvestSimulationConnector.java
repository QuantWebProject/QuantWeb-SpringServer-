package com.quantweb.springserver.domain.investment_simulation.service;

import com.quantweb.springserver.domain.investment_simulation.dto.request.FirstFastApiInvestSimulationRequest;

import com.quantweb.springserver.domain.investment_simulation.dto.request.FirstInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.request.FirstStrategySetup;
import com.quantweb.springserver.domain.investment_simulation.dto.request.RenewFastApiInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.request.RenewInvestSimulationRequest;
import com.quantweb.springserver.domain.investment_simulation.dto.request.RenewStrategySetup;
import com.quantweb.springserver.domain.investment_simulation.dto.request.TechnicalAnalysisStrategy;
import com.quantweb.springserver.domain.investment_simulation.dto.request.ValueInvestmentStrategy;
import com.quantweb.springserver.domain.investment_simulation.dto.request.ValueInvestmentStrategy.Option;
import com.quantweb.springserver.domain.investment_simulation.dto.response.FirstFastApiInvestSimulationResponse;
import com.quantweb.springserver.domain.investment_simulation.dto.response.RenewFastApiInvestSimulationResponse;
import com.quantweb.springserver.domain.user.entity.User;
import com.quantweb.springserver.domain.value_investing_strategy.entity.ValueInvestingStrategy;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@RequiredArgsConstructor
@Component
public class InvestSimulationConnector {

  private static final String BEARER_TYPE = "Bearer";
  private static final RestTemplate REST_TEMPLATE = new RestTemplate();
  private final String FAST_API_URL = "http://localhost:8000";


  public FirstFastApiInvestSimulationResponse requestFirstInvestSimulation(User user,
                                                                           FirstInvestSimulationRequest request) {

    TechnicalAnalysisStrategy technicalAnalysisStrategy = setFirstTechnicalAnalysisStrategy(request.getTechnicalAnalysisStrategy().getTechnicalAnalysisStrategy());

    ValueInvestmentStrategy valueInvestmentStrategy = setOption(request.getValueInvestmentStrategy());

    FirstStrategySetup strategySetup = FirstStrategySetup.builder()
            .strategyName(request.getStrategyName())
            .stockSelection(request.getStockSelection())
            .rebalancingPeriod(request.getRebalancingPeriod())
            .ohlcv("close")
            .backtestStrategy(request.getBacktestStrategy())
            .technicalAnalysisStrategy(technicalAnalysisStrategy)
            .valueInvestmentStrategy(valueInvestmentStrategy)
            .build();

    HttpHeaders headers = setStrategyHeaders();

    FirstFastApiInvestSimulationRequest requestBody = setFirstStrategyBody(user, strategySetup);

    HttpEntity<FirstFastApiInvestSimulationRequest> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<FirstFastApiInvestSimulationResponse> responseEntity = REST_TEMPLATE.exchange(FAST_API_URL+"/invest-simulation/first", HttpMethod.POST,
            requestEntity, FirstFastApiInvestSimulationResponse.class);

    return responseEntity.getBody();
  }


  public RenewFastApiInvestSimulationResponse requestRenewInvestSimulation(User user, RenewInvestSimulationRequest request,int initialAmount,float fee) {
    TechnicalAnalysisStrategy technicalAnalysisStrategy = setFirstTechnicalAnalysisStrategy(request.getTechnicalAnalysisStrategy());

    RenewStrategySetup strategySetup = RenewStrategySetup.builder()
            .strategyName(request.getStrategyName())
            .stockSelection(request.getStockSelection())
            .initialAmount(initialAmount)
            .fee(fee)
            .rebalancingPeriod(request.getRebalancingPeriod())
            .ohlcv("close")
            .backtestStrategy(request.getBacktestStrategy())
            .technicalAnalysisStrategy(technicalAnalysisStrategy)
            .build();

    HttpHeaders headers = setStrategyHeaders();

    RenewFastApiInvestSimulationRequest requestBody = setRenewStrategyBody(user, strategySetup);

    HttpEntity<RenewFastApiInvestSimulationRequest> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<RenewFastApiInvestSimulationResponse> responseEntity = REST_TEMPLATE.exchange(FAST_API_URL+"/invest-simulation/renew", HttpMethod.POST,
            requestEntity, RenewFastApiInvestSimulationResponse.class);

    return responseEntity.getBody();
  }

  private FirstFastApiInvestSimulationRequest setFirstStrategyBody(User user, FirstStrategySetup strategySetup) {
    return FirstFastApiInvestSimulationRequest.builder()
            .userName(user.getName())
            .strategySetup(strategySetup)
            .build();
  }

  private RenewFastApiInvestSimulationRequest setRenewStrategyBody(User user, RenewStrategySetup strategySetup) {
    return RenewFastApiInvestSimulationRequest.builder()
            .userName(user.getName())
            .strategySetup(strategySetup)
            .build();
  }

  private HttpHeaders setStrategyHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    return headers;
  }

  private TechnicalAnalysisStrategy setFirstTechnicalAnalysisStrategy(TechnicalAnalysisStrategy technicalAnalysisStrategy) {
    return TechnicalAnalysisStrategy.builder()
            .staticAssetAllocation(
                    Optional.ofNullable(technicalAnalysisStrategy.getStaticAssetAllocation()).orElse(null))
            .tatcticalAssetAllocation(
                    Optional.ofNullable(technicalAnalysisStrategy.getTatcticalAssetAllocation())
                            .orElse(null))
            .macd(Optional.ofNullable(technicalAnalysisStrategy.getMacd()).orElse(null))
            .trendFollowing(
                    Optional.ofNullable(technicalAnalysisStrategy.getTrendFollowing()).orElse(null))
            .rsi(Optional.ofNullable(technicalAnalysisStrategy.getRsi()).orElse(null))
            .bollingerBands(
                    Optional.ofNullable(technicalAnalysisStrategy.getBollingerBands()).orElse(null))
            .build();
  }

  private ValueInvestmentStrategy setOption(List<ValueInvestingStrategy> strategies) {
    ValueInvestmentStrategy valueInvestmentStrategy = new ValueInvestmentStrategy();
    List<Option> options = valueInvestmentStrategy.getOptions();
    strategies
            .forEach(
                    strategy ->
                    {
                      Option option = Option.builder()
                              .factor(strategy.getType().getFactor())
                              .range(Arrays.asList(strategy.getStartValue(), strategy.getEndValue()))
                              .build();
                      options.add(option);
                    }
            );
    return valueInvestmentStrategy;
  }


}

