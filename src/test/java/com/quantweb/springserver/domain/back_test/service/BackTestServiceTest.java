package com.quantweb.springserver.domain.back_test.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.quantweb.springserver.domain.back_test.DTO.BackTestInput;
import com.quantweb.springserver.domain.back_test.DTO.InvestmentResult;
import com.quantweb.springserver.utils.LocalDateAdapter;
import com.quantweb.springserver.utils.LocalDateTimeAdapter;

@SpringBootTest
class BackTestServiceTest {
	@Autowired
	private BackTestService backTestService;
	@Test
	public void backtestTest(){
		String json = "{\n"
			+ "    \"strategy_setup\": {\n"
			+ "      \"strategy_name\": \"start_backtest\",\n"
			+ "      \"stock_selection\": 10,\n"
			+ "      \"period\": {\n"
			+ "        \"start_date\": \"2020-01-01\",\n"
			+ "        \"end_date\": \"2023-01-01\"\n"
			+ "      },\n"
			+ "      \"initial_amount\": 1000000,\n"
			+ "      \"fee\": 0.2,\n"
			+ "      \"rebalancing_period\": \"once\",\n"
			+ "      \"ohlcv\": \"close\",\n"
			+ "      \"backtest_strategy\": \"macd\",\n"
			+ "      \"technical_analysis_strategy\": [\n"
			+ "        {\n"
			+ "          \"static_asset_allocation\": {\n"
			+ "            \"rebalance\": \"monthly\"\n"
			+ "          }\n"
			+ "        },\n"
			+ "        {\n"
			+ "          \"tactical_asset_allocation\": {\n"
			+ "            \"rebalance\": \"monthly\",\n"
			+ "            \"n_s\": 3,\n"
			+ "            \"year_s\": 1\n"
			+ "          }\n"
			+ "        },\n"
			+ "        {\n"
			+ "          \"macd\": {\n"
			+ "            \"fastperiods\": 12,\n"
			+ "            \"slowperiods\": 26,\n"
			+ "            \"signalperiods\": 9\n"
			+ "          }\n"
			+ "        },\n"
			+ "        {\n"
			+ "          \"trend_following\": {\n"
			+ "            \"sma_period\": 50\n"
			+ "          }\n"
			+ "        },\n"
			+ "        {\n"
			+ "          \"rsi\": {\n"
			+ "            \"high_rsi\": 70,\n"
			+ "            \"low_rsi\": 30\n"
			+ "          }\n"
			+ "        },\n"
			+ "        {\n"
			+ "          \"bollinger_bands\": {\n"
			+ "            \"timeperiod_input\": 20,\n"
			+ "            \"nbdevup_input\": 2,\n"
			+ "            \"nbdevdn_input\": 2\n"
			+ "          }\n"
			+ "        }\n"
			+ "      ],\n"
			+ "      \"value_investment_strategy\": {\n"
			+ "        \"options\": [\n"
			+ "          {\"factor\": \"pbr\", \"percent\": [50, 100], \"range\":null},\n"
			+ "          {\"factor\": \"per\", \"percent\": null, \"range\": [-3, 2.5]},\n"
			+ "          {\"factor\": \"psr\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"p/cf\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"market_cap\", \"percent\": [50, 100], \"range\": null},\n"
			+ "          {\"factor\": \"profitability_percentile\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"gross_margin\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"operating_margin\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"net_profit_margin\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"roe\", \"percent\": null, \"range\": null},\n"
			+ "          {\"factor\": \"roa\", \"percent\": null, \"range\": null}\n"
			+ "        ]\n"
			+ "      }\n"
			+ "    }\n"
			+ "  }\n"
			+ "  \n"
			+ "  ";
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
		BackTestInput backTestInput = gson.fromJson(json, BackTestInput.class);
		System.out.println(backTestInput);
		InvestmentResult investmentResult = backTestService.backtestAndSave(backTestInput);
		System.out.println(gson.toJson(investmentResult));


	}
	@Test
	public void gsonTest(){
		String returnJson = """
        {
            "investment_result": {
                "final_cumulative_return": 15.23,
                "daily_cumulative_return": {
                    "backtest": [
                        {"date": "2020-01-01", "return": 0},
                        {"date": "2020-06-01", "return": 5.12},
                        {"date": "2021-01-01", "return": 7.34},
                        {"date": "2021-06-01", "return": 10.45},
                        {"date": "2022-01-01", "return": 12.89},
                        {"date": "2022-06-01", "return": 15.23}
                    ],
                    "us500": [
                        {"date": "2020-01-01", "return": 0},
                        {"date": "2020-06-01", "return": 4.12},
                        {"date": "2021-01-01", "return": 5.34},
                        {"date": "2021-06-01", "return": 11.45},
                        {"date": "2022-01-01", "return": 8.89},
                        {"date": "2022-06-01", "return": 12.23}
                    ]
                },
                "mdd": {"backtest": -10, "us500": -14},
                "max_drawdown_graph": {
                    "backtest" : [
                        {"date": "2020-01-01", "mdd": -10},
                        {"date": "2020-06-01", "mdd": -2.45},
                        {"date": "2021-01-01", "mdd": -3.67},
                        {"date": "2021-06-01", "mdd": -2.89},
                        {"date": "2022-01-01", "mdd": -1.45},
                        {"date": "2023-01-01", "mdd": -1.23}
                    ],
                    "us500" : [
                        {"date": "2020-01-01", "mdd": -11},
                        {"date": "2020-06-01", "mdd": -3.45},
                        {"date": "2021-01-01", "mdd": -6.67},
                        {"date": "2021-06-01", "mdd": -7.89},
                        {"date": "2022-01-01", "mdd": -2.45},
                        {"date": "2023-01-01", "mdd": -1.23}
                    ]
                },
                "average_stock_price" : [
                    {"date": "2020-01-01", "average_open": 100, "average_close": 110},
                    {"date": "2020-06-01", "average_open": 110, "average_close": 90},
                    {"date": "2021-01-01", "average_open": 120, "average_close": 70},
                    {"date": "2021-06-01", "average_open": 130, "average_close": 120},
                    {"date": "2022-01-01", "average_open": 140, "average_close": 130},
                    {"date": "2023-01-01", "average_open": 150, "average_close": 110}
                ],
                "creation_date": "2020-01-01",
                "period": {
                    "start_date": "2020-01-01",
                    "end_date": "2023-01-01"
                },
                "initial_amount": 1000000,
                "realized_profit": 152300,
                "unrealized_profit": 50000,
                "final_asset": 1200000,
                "annualized_return": 4.87,
                "transaction_history_graph": [
                    {"date": "2020-01-01", "action": "buy", "amount": 1000000, "ticker": "MSFT"},
                    {"date": "2020-06-01", "action": "sell", "amount": 200000, "ticker": "MSFT"},
                    {"date": "2021-01-01", "action": "buy", "amount": 150000, "ticker": "MSFT"},
                    {"date": "2021-06-01", "action": "sell", "amount": 100000, "ticker": "MSFT"},
                    {"date": "2022-01-01", "action": "buy", "amount": 50000, "ticker": "MSFT"},
                    {"date": "2023-01-01", "action": "sell", "amount": 250000, "ticker": "MSFT"}
                ]
            },
            "strategy_info": {
                "strategy_name": "start_backtest",
                "rebalancing_period": "once",
                "strategy_used": "MACD",
                "stock": [
                    {"ticker" : "MSFT", "sector" : "Technology", "percentage": 0.4},
                    {"ticker" : "AAPL", "sector" : "Healthcare", "percentage": 0.2},
                    {"ticker" : "HAN", "sector" : "Energy", "percentage": 0.4}
                ],
                "investment_sectors_pie_chart": [
                    {"sector": "Technology", "percentage": 0.4},
                    {"sector": "Healthcare", "percentage": 0.3},
                    {"sector": "Finance", "percentage": 0.2},
                    {"sector": "Energy", "percentage": 0.1}
                ]
            }
        }
        """;

		Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
		try {
			JsonElement jsonElement = JsonParser.parseString(returnJson);
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonObject investmentResultJson = jsonObject.getAsJsonObject("investment_result");

			// 단계별로 JSON에서 특정 부분을 추출하여 확인
			System.out.println("investment_result: " + investmentResultJson.toString());

			InvestmentResult investmentResult = gson.fromJson(investmentResultJson, InvestmentResult.class);
			System.out.println("InvestmentResult: " + gson.toJson(investmentResult));
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
	}
}
