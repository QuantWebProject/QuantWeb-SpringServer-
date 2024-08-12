package com.quantweb.springserver.domain.account.service;

import com.quantweb.springserver.domain.account.dto.request.KoreaInvestmentMockAccountRequest;
import com.quantweb.springserver.domain.account.dto.response.AccountResponse;
import com.quantweb.springserver.domain.account.entity.Account;
import com.quantweb.springserver.domain.account.entity.AccountRepository;
import com.quantweb.springserver.domain.account.entity.AccountType;
import com.quantweb.springserver.domain.user.entity.User;
import com.quantweb.springserver.domain.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

  private final AccountRepository accountRepository;
  private final UserRepository userRepository;

  public AccountResponse getKoreaInvestmentMockAccount(Long userId) {
    Account account =
        accountRepository
            .findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("계좌가 없습니다"));

    return AccountResponse.builder()
        .type(account.getType())
        .appKey(account.getAppKey())
        .secretKey(account.getSecretKey())
        .accountNumber(account.getAccountNumber())
        .build();
  }

  @Transactional
  public void syncKoreaInvestmentMockAccount(
      Long userId, KoreaInvestmentMockAccountRequest request) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 없습니다"));

    if (checkMockAccountExist(user)) {
      throw new IllegalArgumentException("이미 모의계좌가 존재합니다");
    }
    Account account =
        Account.builder()
            .user(user)
            .type(AccountType.MOCK)
            .appKey(request.getAppKey())
            .secretKey(request.getSecretKey())
            .accountNumber(request.getAccountNumber())
            .build();
    accountRepository.save(account);
  }

  private boolean checkMockAccountExist(User user) {
    return accountRepository.existsByUserAndType(user, AccountType.MOCK);
  }

  @Transactional
  public void updateKoreaInvestmentMockAccount(
      Long userId, KoreaInvestmentMockAccountRequest request) {

    Account account =
        accountRepository
            .findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("계좌가 없습니다"));

    account.updateKoreaInvestmentMockAccount(
        request.getAppKey(), request.getSecretKey(), request.getAccountNumber());
  }

  @Transactional
  public void deleteKoreaInvestmentMockAccount(Long userId) {
    Account account =
        accountRepository
            .findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("계좌가 없습니다"));

    accountRepository.delete(account);
  }
}
