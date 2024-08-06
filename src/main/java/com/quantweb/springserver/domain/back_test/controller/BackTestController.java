package com.quantweb.springserver.domain.back_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.back_test.DTO.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.InvestmentResult;
import com.quantweb.springserver.domain.back_test.service.BackTestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BackTestController {
	private final BackTestService backTestService;

	@PostMapping("/backtest")
	@Authenticated
	public InvestmentResult backTest(@RequestBody BackTestInput backTestInput) {
		return backTestService.backtestAndSave(backTestInput);
	}
}
