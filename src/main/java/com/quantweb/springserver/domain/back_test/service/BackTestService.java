package com.quantweb.springserver.domain.back_test.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.quantweb.springserver.domain.back_test.DTO.response.BackTestResultDto;
import com.quantweb.springserver.domain.back_test.DTO.response.StrategyInfoDto;
import com.quantweb.springserver.domain.back_test.converter.BackTestConverter;
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.graph.converter.GraphConverter;
import com.quantweb.springserver.domain.graph.entity.Graph;
import com.quantweb.springserver.domain.graph.service.GraphService;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.service.PieChartService;
import com.quantweb.springserver.domain.sales_transaction_history.service.TransactionHistoryService;
import com.quantweb.springserver.domain.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.response.InvestmentResultDto;
import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.utils.LocalDateAdapter;
import com.quantweb.springserver.utils.LocalDateTimeAdapter;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BackTestService {

	private final BackTestRepository backTestRepository;

	private final GraphService graphService;
	private final PieChartService pieChartService;
	private final TransactionHistoryService	transactionHistoryService;
	private final StockService stockService;

	@Transactional
	public BackTestResultDto backtestAndSave(BackTestInput backTestInput) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://ec2-43-203-37-134.ap-northeast-2.compute.amazonaws.com:8000/backtesting";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
		String json = gson.toJson(backTestInput);
		System.out.format("Json: %s%n",json);

		HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
		String returnJson = restTemplate.postForObject(url, requestEntity, String.class);
		//System.out.println(gson.toJson(returnJson));

		JsonElement jsonElement = JsonParser.parseString(returnJson);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonObject investmentResultJson = jsonObject.getAsJsonObject("investment_result");
		JsonObject strategyInfoJson = jsonObject.getAsJsonObject("strategy_info");


		// 단계별로 JSON에서 특정 부분을 추출하여 확인
		System.out.format("Result: %s%n", jsonObject.toString());

		InvestmentResultDto investmentResultDto = gson.fromJson(investmentResultJson, InvestmentResultDto.class);
		StrategyInfoDto strategyInfoDto = gson.fromJson(strategyInfoJson, StrategyInfoDto.class	);

		saveBackTest(backTestInput, investmentResultDto, strategyInfoDto);

		return new BackTestResultDto(investmentResultDto,strategyInfoDto);
	}

	private void saveBackTest(BackTestInput backTestInput, InvestmentResultDto investmentResultDto, StrategyInfoDto strategyInfoDto) {
		BackTest newBackTest = BackTestConverter.toBackTest(backTestInput, investmentResultDto);

		Graph graph = GraphConverter.toBackTestGraph(newBackTest);

		graphService.saveGraph(newBackTest, graph);
		graphService.saveDailyPercentage(investmentResultDto.getDaily_cumulative_return().getBacktest(), graph);
		graphService.saveDailyPercentageUs500(investmentResultDto.getDaily_cumulative_return().getUs500(), graph);
		graphService.saveMdd(investmentResultDto.getMax_drawdown_graph().getBacktest(), graph);
		graphService.saveMddUs500(investmentResultDto.getMax_drawdown_graph().getUs500(), graph);

		pieChartService.savePieChart(strategyInfoDto.getInvestment_sectors_pie_chart(), newBackTest);
		transactionHistoryService.saveTransactionHistory(investmentResultDto.getTransaction_history_graph(), newBackTest);

		stockService.saveStock(strategyInfoDto.getStock(),newBackTest);

		backTestRepository.save(newBackTest);
	}

}
