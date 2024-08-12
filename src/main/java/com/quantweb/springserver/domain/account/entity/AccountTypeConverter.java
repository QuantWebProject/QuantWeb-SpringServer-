package com.quantweb.springserver.domain.account.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

  @Override
  public String convertToDatabaseColumn(AccountType accountType) {
    return accountType.getName();
  }

  @Override
  public AccountType convertToEntityAttribute(String dbData) {
    return AccountType.fromName(dbData);
  }
}
