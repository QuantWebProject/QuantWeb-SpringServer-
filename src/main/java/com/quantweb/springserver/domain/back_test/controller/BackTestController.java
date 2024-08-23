package com.quantweb.springserver.domain.back_test.controller;

import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import com.quantweb.springserver.domain.back_test.DTO.request.BackTestInput;

import com.quantweb.springserver.domain.back_test.DTO.request.BackTestRequestDto;
import com.quantweb.springserver.domain.back_test.DTO.response.BackTestDetailsDto;
import com.quantweb.springserver.domain.back_test.DTO.response.BackTestResponseDto;

import com.quantweb.springserver.domain.stock.dto.response.StockResponseDto;
import com.quantweb.springserver.domain.stock.service.StockService;

import com.quantweb.springserver.domain.sales_transaction_history.dto.response.TransactionHistoryResponseDto;
import com.quantweb.springserver.domain.sales_transaction_history.service.TransactionHistoryService;

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

import org.springframework.http.ResponseEntity;

import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backtests")
public class BackTestController {

	private final BackTestService backTestService;
    private final StockService stockService;
    private final TransactionHistoryService transactionHistoryService;

	@PostMapping
    @Operation(summary = "백테스트 실행하기 API",description = "백테스팅 실행하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST400", description = "입력 값이 잘못되었습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
	@Authenticated
	public ResponseEntity<BackTestResponseDto.BackTestCreateDto> createBackTest(@RequestBody BackTestInput backTestInput) {
		return ResponseEntity.ok(backTestService.createBackTest(backTestInput));
	}

    @PostMapping("/save")
    @Operation(summary = "백테스트 저장하기 API",description = "백테스팅 저장하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST400", description = "이미 동일한 전략 이름이 존재합니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "backtestName", description = "전략 이름 입력"),

    })
    @Authenticated
    public ResponseEntity<BackTestResponseDto.BackTestSaveDto> saveBackTest(@AuthenticationPrincipal Long userId,
                                                                            @RequestBody BackTestRequestDto.BackTestSaveDto backTestResult) {
        return ResponseEntity.ok(backTestService.saveBackTest(userId, backTestResult));
    }

    @GetMapping("/{backtestId}/details")
    @Operation(summary = "백테스트 기존 전략 결과 상세 조회 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST404", description = "백테스트가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "backtestId", description = "조회할 백테스트 Id"),

    })
    @Authenticated
    public ResponseEntity<BackTestDetailsDto.GetBackTestDto> getResultDetails(@PathVariable("backtestId") Long backtestId){

        BackTestDetailsDto.GetBackTestDto resultDto = backTestService.getDetailsResult(backtestId);

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

    @GetMapping
    @Operation(summary = "내 백테스트 전략들 조회 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저 정보가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저 Id"),

    })
    @Authenticated
    public ResponseEntity<List<BackTestResponseDto.BackTestSaveDto>> getMyBacktests(@AuthenticationPrincipal @PathVariable("userId") Long userId){

        List<BackTestResponseDto.BackTestSaveDto> resultDto = backTestService.getMyBacktests(userId);

        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/{backtestId}/histories")
    @Operation(summary = "백테스트 매매내역 조회 API",description = "")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST404", description = "백테스트가 존재하지 않습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Parameters({
            @Parameter(name = "backtestId", description = "조회할 매매내역의 백테스트 Id"),

    })
    @Authenticated
    public ResponseEntity<TransactionHistoryResponseDto> getTrasactionHistory(@AuthenticationPrincipal @PathVariable("userId") Long userId,
                                                                              @PathVariable("backtestId") Long backtestId){

        TransactionHistoryResponseDto resultDto = transactionHistoryService.getTransactionHistory(userId, backtestId);

        return ResponseEntity.ok(resultDto);
    }


    @DeleteMapping("/{backtestId}")
    @Operation(summary = "백테스트 삭제하기 API",description = "백테스팅 삭제하기")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "BACKTEST400", description = "백테스트 이름이 중복되었습니다.",content = @Content(schema = @Schema(implementation = EntityResponse.class))),
    })
    @Authenticated
    public ResponseEntity<BackTestResponseDto.BackTestSaveDto> deleteBackTest(@AuthenticationPrincipal Long userId, @PathVariable("backtestId") Long backtestId) {
        return ResponseEntity.ok(backTestService.deleteBacktest(userId, backtestId));
    }
}
