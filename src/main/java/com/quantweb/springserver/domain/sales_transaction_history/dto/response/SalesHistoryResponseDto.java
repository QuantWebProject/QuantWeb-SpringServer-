package com.quantweb.springserver.domain.sales_transaction_history.dto.response;

import com.quantweb.springserver.domain.sales_transaction_history.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SalesHistoryResponseDto {
    /**
     * 용어가 헷갈리는게 많아서 erd 수정할 때 설명란에 자세히 적어야할 것 같습니다.
     * **/

    private LocalDateTime transactionDate; //거래일시
    private String stockName;  //종목
    private LocalDateTime period;   //평균보유기간
    private Integer realizedProfit; //실현손익
    private Float profitPercentage; //수익률
    private Float buyQuantity;  //매수수량
    private Float sellQuantity; //매도수량
    private Integer buyPrice;   //매수금액
    private Integer sellPrice;   //매도금액
    private TransactionType status; //상태: 거래완료
    private Integer fee;    //수수료

}
