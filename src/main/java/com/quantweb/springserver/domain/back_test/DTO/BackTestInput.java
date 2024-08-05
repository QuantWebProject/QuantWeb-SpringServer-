package com.quantweb.springserver.domain.back_test.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class BackTestInput {
	@SerializedName("strategy_setup")
	StrategySetup strategySetup;
}
