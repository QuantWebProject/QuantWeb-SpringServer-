package com.quantweb.springserver.domain.back_test.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.quantweb.springserver.domain.back_test.DTO.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.InvestmentResult;
import com.quantweb.springserver.domain.back_test.repository.BackTestRepository;
import com.quantweb.springserver.utils.LocalDateAdapter;
import com.quantweb.springserver.utils.LocalDateTimeAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BackTestService {
	private final BackTestRepository backTestRepository;

	public InvestmentResult backtestAndSave(BackTestInput backTestInput) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8000/backtesting";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
		String json = gson.toJson(backTestInput);
		System.out.println(json);
		HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
		String returnJson = restTemplate.postForObject(url, requestEntity, String.class);
		// System.out.println(gson.toJson(returnJson));
		JsonElement jsonElement = JsonParser.parseString(returnJson);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonObject investmentResultJson = jsonObject.getAsJsonObject("investment_result");

		// 단계별로 JSON에서 특정 부분을 추출하여 확인
		System.out.println("investment_result: " + investmentResultJson.toString());

		InvestmentResult investmentResult = gson.fromJson(investmentResultJson, InvestmentResult.class);


		System.out.println("InvestmentResult: " + gson.toJson(investmentResult));
		return investmentResult;
	}

}
