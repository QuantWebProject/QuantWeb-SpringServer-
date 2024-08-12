package com.quantweb.springserver.domain.account.entity;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum AccountType {
  MOCK("모의계좌"),
  REAL("실제계좌");

  private final String name;

  AccountType(String name) {
    this.name = name;
  }

  public static AccountType fromName(String name) {
    return Arrays.stream(AccountType.values())
        .filter(e -> e.getName().equals(name))
        .findAny()
        .orElse(null);
  }
}
