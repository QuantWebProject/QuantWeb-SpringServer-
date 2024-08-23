package com.quantweb.springserver.domain.sales_transaction_history.dto.response;

import com.quantweb.springserver.domain.sales_transaction_history.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TransactionHistoryResponseDto {

    private List<TransactionHistory> transactionHistories;

    private List<TransactionHistoryGraph> transactionHistoryGraph;  //매매내역 - 그래프

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class TransactionHistory {
        private LocalDate transactionDate; //거래일시
        private String stockName;  //종목
        //private LocalDate period;   //평균보유기간
        private Float realizedProfit; //실현손익
        private Float profit; //수익률
        private Float buyQuantity;  //매수수량
        private Float sellQuantity; //매도수량
        private Long buyPrice;   //매수금액
        private Long sellPrice;   //매도금액
        //private TransactionType status; //상태: 거래완료
        private Float fee;    //수수료
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class TransactionHistoryGraph {
        private LocalDate date;
        private Long amount;
        private TransactionType action;
        private String ticker;
    }
}
