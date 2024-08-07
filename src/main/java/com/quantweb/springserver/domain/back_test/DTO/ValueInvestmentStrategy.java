package com.quantweb.springserver.domain.back_test.DTO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ValueInvestmentStrategy {
	@SerializedName("options")
	private List<ValueInvestmentOption> options;
	@Data
	public class ValueInvestmentOption {
		@SerializedName("factor")
		private String factor;

		@SerializedName("percent")
		private List<Float> percent;

		@SerializedName("range")
		private List<Float> range;
	}
}
