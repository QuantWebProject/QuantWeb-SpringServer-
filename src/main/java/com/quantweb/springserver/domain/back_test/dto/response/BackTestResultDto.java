package com.quantweb.springserver.domain.back_test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackTestResultDto {

    private Long backtestId;
    private String backtestName;
}
