package com.quantweb.springserver.domain.tech_analy_strategy_variables.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StrategyTypeConverter implements AttributeConverter<StrategyType, String> {

  @Override
  public String convertToDatabaseColumn(StrategyType strategyType) {
    return strategyType.getFactor();
  }

  @Override
  public StrategyType convertToEntityAttribute(String dbData) {
    return StrategyType.fromFactor(dbData);
  }
}
