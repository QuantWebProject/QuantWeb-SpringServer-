package com.quantweb.springserver.domain.account.controller;

import com.quantweb.springserver.domain.account.dto.request.KoreaInvestmentMockAccountRequest;
import com.quantweb.springserver.domain.account.dto.response.AccountResponse;
import com.quantweb.springserver.domain.account.service.AccountService;
import com.quantweb.springserver.domain.auth.config.Authenticated;
import com.quantweb.springserver.domain.auth.config.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/korea-investment")
public class AccountController {

  private final AccountService accountService;

  @Authenticated
  @GetMapping("/sync/mock-account")
  public ResponseEntity<AccountResponse> getKoreaInvestmentMockAccount(
      @AuthenticationPrincipal Long userId) {
    AccountResponse accountResponse = accountService.getKoreaInvestmentMockAccount(userId);

    return ResponseEntity.ok(accountResponse);
  }

  @Authenticated
  @PostMapping("/sync/mock-account")
  public ResponseEntity<Void> syncKoreaInvestmentMockAccount(
      @AuthenticationPrincipal Long userId,
      @RequestBody KoreaInvestmentMockAccountRequest request) {
    accountService.syncKoreaInvestmentMockAccount(userId, request);

    return ResponseEntity.ok().build();
  }

  @Authenticated
  @PutMapping("/sync/mock-account")
  public ResponseEntity<Void> updateKoreaInvestmentMockAccount(
      @AuthenticationPrincipal Long userId,
      @RequestBody KoreaInvestmentMockAccountRequest request) {
    accountService.updateKoreaInvestmentMockAccount(userId, request);

    return ResponseEntity.ok().build();
  }

  @Authenticated
  @DeleteMapping("/sync/mock-account")
  public ResponseEntity<Void> deleteKoreaInvestmentMockAccount(
      @AuthenticationPrincipal Long userId) {
    accountService.deleteKoreaInvestmentMockAccount(userId);

    return ResponseEntity.ok().build();
  }
}
