package com.quantweb.springserver.domain.back_test.controller;

import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quantweb.springserver.domain.auth.config.Authenticated;

import com.quantweb.springserver.domain.back_test.service.BackTestService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backtests")
public class BackTestController {
	private final BackTestService backTestService;

	@PostMapping("")
	@Authenticated
	public Object backTest(@RequestBody BackTestInput backTestInput) {
		return backTestService.backtestAndSave(backTestInput);
	}
}
