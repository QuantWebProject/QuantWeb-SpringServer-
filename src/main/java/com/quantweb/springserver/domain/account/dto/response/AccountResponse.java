package com.quantweb.springserver.domain.account.dto.response;

import com.quantweb.springserver.domain.account.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AccountResponse {
  private AccountType type;
  private String appKey;
  private String secretKey;
  private String accountNumber;
}
