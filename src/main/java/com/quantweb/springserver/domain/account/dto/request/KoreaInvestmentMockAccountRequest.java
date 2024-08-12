package com.quantweb.springserver.domain.account.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KoreaInvestmentMockAccountRequest {

  @NotNull private String appKey;

  @NotNull private String secretKey;

  @NotNull private String accountNumber;
}
