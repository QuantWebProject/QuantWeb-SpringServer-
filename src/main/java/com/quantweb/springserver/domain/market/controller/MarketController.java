package com.quantweb.springserver.domain.market.controller;

import com.quantweb.springserver.common.entity.CustomResponseEntity;
import com.quantweb.springserver.domain.market.dto.request.MarketCreateRequest;
import com.quantweb.springserver.domain.market.dto.request.MarketUpdateRequest;
import com.quantweb.springserver.domain.market.dto.response.MarketIdResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketPagingResponse;
import com.quantweb.springserver.domain.market.dto.response.MarketSummaryResponse;
import com.quantweb.springserver.domain.market.service.MarketService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "마켓 API", description = "마켓 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
public class MarketController {
    private final MarketService marketService;

    @Operation(summary = "마켓 전략 등록 API")
    @PostMapping("")
    public ResponseEntity<MarketIdResponse> createMarket(@Valid @RequestBody MarketCreateRequest request){
        return ResponseEntity.ok(marketService.createMarket(request));
    }
    @Operation(summary = "마켓 전략 수정 API")
    @PatchMapping(value = "/{marketId}")
    public ResponseEntity<MarketIdResponse> updateMarket(
            @Parameter(description= "수정할 마켓 Id")@PathVariable Long marketId,
            @Valid @RequestBody MarketUpdateRequest request){
        return ResponseEntity.ok(marketService.updateMarket(marketId, request));
    }

    @Operation(summary = "마켓 삭제 API")
    @DeleteMapping("/{marketId}")
    public ResponseEntity<MarketIdResponse> deleteMarket(
            @Parameter(description= "삭제할 마켓 Id")@PathVariable Long marketId){
        return ResponseEntity.ok(marketService.deleteMarket(marketId));
    }

    @Operation(summary = "마켓 백테스트 전략 최신순 조회 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 백 테스트 개수"),
    })
    @GetMapping("/backtests/recent")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketBackTestByRecent(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketBackTestByCreatedAt(userId, page, size));
    }

    @Operation(summary = "마켓 모의투자 전략 최신순 조회 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 모의투자 개수"),
    })
    @GetMapping("/investments/recent")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketInvestmentByRecent(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketInvestmentSimulationByCreatedAt(userId, page,size));
    }

    @Operation(summary = "마켓 백테스트 전략 추천순 조회 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 백 테스트 개수"),
    })
    @GetMapping("/backtests/recommend")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketBackTestByRecommend(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketBackTestByRecommend(userId, page, size));
    }

    @Operation(summary = "마켓 모의투자 전략 추천순 조회 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 백 테스트 개수"),
    })
    @GetMapping("/investmentSimulation/recommend")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketInvestmentSimulationByRecommend(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketInvestmentSimulationByRecommend(userId, page, size));
    }

    @Operation(summary = "마켓 백테스트 전략 검색 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "keyword", description = "검색할 키워드"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 백 테스트 개수"),
    })
    @GetMapping("/backtests/search")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketBackTestBySearch(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketBackTestByKeyword(userId, keyword, page, size));
    }

    @Operation(summary = "마켓 모의투자 전략 검색 API")
    @Parameters(value = {
            @Parameter(name = "userId", description = "로그인된 유저 Id"),
            @Parameter(name = "keyword", description = "검색할 키워드"),
            @Parameter(name = "page", description = "페이지 번호(0부터 시작)"),
            @Parameter(name = "size", description = "한 페이지 당 모의투자 개수"),
    })
    @GetMapping("/investmentSimulation/search")
    public ResponseEntity<MarketPagingResponse<MarketSummaryResponse>> inquiryMarketInvestmentSimulationBySearch(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(marketService.inquiryMarketInvestmentSimulationByKeyword(userId, keyword, page, size));
    }

    @Operation(summary = "마켓 좋아요 누르기 API")
    @PostMapping("/{marketId}/like")
    public ResponseEntity<Boolean> likeMarket(
            @Parameter(description = "로그인 된 유저 id")@RequestParam Long memberId,
            @Parameter(description = "좋아요 누를 마켓 전략 id") @PathVariable("marketId") Long marketId
    ){
        return ResponseEntity.ok(marketService.likeMarket(marketId, memberId));
    }

    @Operation(summary = "마켓 구독 누르기 API")
    @PostMapping("/{marketId}/subscribe")
    public ResponseEntity<Boolean> subscribeMarket(
            @Parameter(description = "로그인 된 유저 id")@RequestParam Long memberId,
            @Parameter(description = "구독 누를 마켓 전략 id") @PathVariable("marketId") Long marketId
    ){
        return ResponseEntity.ok(marketService.subscribeMarket(marketId, memberId));
    }
}
