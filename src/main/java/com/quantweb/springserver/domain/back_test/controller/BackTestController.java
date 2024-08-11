package com.quantweb.springserver.domain.back_test.controller;

import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;
import com.quantweb.springserver.domain.stock.dto.response.StockResponseDto;
import com.quantweb.springserver.domain.stock.service.StockService;
import org.springframework.web.bind.annotation.*;

import com.quantweb.springserver.domain.auth.config.Authenticated;

import com.quantweb.springserver.domain.back_test.service.BackTestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import com.quantweb.springserver.domain.back_test.DTO.response.BackTestResponseDto;
import org.springframework.http.ResponseEntity;

import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backtests")
public class BackTestController {

	private final BackTestService backTestService;
    private final StockService stockService;

	@PostMapping
    @Operation(summary = "백테스트 실행하기 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST400", description = "백테스트 이름이 중복되었습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
	@Authenticated
	public Object backTest(@RequestBody BackTestInput backTestInput) {
		return backTestService.backtestAndSave(backTestInput);
	}

    @GetMapping("/{backtestId}")
    @Operation(summary = "백테스트 기존 전략 결과 상세 조회 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST404", description = "백테스트가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "backtestId", description = "조회할 백테스트 Id"),

    })
    public ResponseEntity<BackTestResponseDto.GetBackTestDto> getResultDetails(@PathVariable("backtestId") Long backtestId){

        BackTestResponseDto.GetBackTestDto resultDto = backTestService.getDetailsResult(backtestId);

        return ResponseEntity.ok(resultDto);
    }


    @GetMapping("/{backtestId}/sectors")
    @Operation(summary = "백테스트 투자 종목 조회 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST404", description = "백테스트가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "backtestId", description = "조회할 백테스트 Id"),

    })
    @Authenticated
    public ResponseEntity<StockResponseDto> getInvestmentSectors(@PathVariable("backtestId") Long backtestId){

        StockResponseDto resultDto = stockService.getInvestmentSectors(backtestId);

        return ResponseEntity.ok(resultDto);
    }
}
