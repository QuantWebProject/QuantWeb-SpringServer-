package com.quantweb.springserver.domain.back_test.repository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.back_test.entity.QBackTest;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestRecommendationResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import com.quantweb.springserver.domain.investment_sectors_pie_chart.entity.InvestmentSectorsPieChart;
import com.quantweb.springserver.domain.market.entity.Market;
import com.quantweb.springserver.domain.market.entity.QMarket;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BackTestRepositoryImpl extends QuerydslRepositorySupport implements BackTestRepositoryCustom {
    int SIZE_LIMIT = 0;
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    public BackTestRepositoryImpl() {
        super(BackTest.class);
    }

    @PostConstruct
    private void init() {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    QBackTest backTest = QBackTest.backTest;
    QMarket market = QMarket.market;

    @Override
    public Page<MyBackTestResponse> getAllBackTestSortByLatest(Pageable pageable, Long userId,String type) {
       SIZE_LIMIT = checkSize(type,pageable);

        List<BackTest> backTests = queryFactory
                .selectFrom(backTest)
                .distinct()
                .join(backTest.user)
                .where(backTest.user.id.eq(userId))
                .orderBy(backTest.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(SIZE_LIMIT)
                .fetch();

        List<MyBackTestResponse> myBackTestResponses = backTests.stream()
                .map(
                        backTest1 -> MyBackTestResponse.builder()
                                .backTestId(backTest1.getId())
                                .name(backTest1.getName())
                                .startedDate(backTest1.getInvestStartDate())
                                .endDate(backTest1.getInvestEndDate())
                                .createdAt(backTest1.getCreatedAt())
                                .yearlyProfit(backTest1.getYearlyAverageProfit())
                                .unrealizedProfit(backTest1.getUnrealized_profit())
                                .mdd(backTest1.getGraph().getMdds().stream().map(mdd -> mdd.getMdd())
                                        .min(Float::compareTo).get())
                                .marketShared(backTest1.getMarketShared())
                                .initInvestmentFund(backTest1.getInitInvestmentFund())
                                .pieCharts(backTest1.getPieCharts().stream().map(
                                        investmentSectorsPieChart -> InvestmentSectorsPieChart.builder()
                                                .sector(investmentSectorsPieChart.getSector())
                                                .percentage(investmentSectorsPieChart.getPercentage())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()
                ).collect(Collectors.toList());

        long totalCount = queryFactory.selectFrom(backTest)
                .distinct()
                .join(backTest.user)
                .where(backTest.user.id.eq(userId))
                .fetchCount();

        return new PageImpl<>(myBackTestResponses, pageable, totalCount);

    }

    private int checkSize(String type,Pageable pageable) {
        if(type.equals("preview")){
            return SIZE_LIMIT = 4;
        }
        else{
           return SIZE_LIMIT = pageable.getPageSize();
        }
    }

    @Override
    public Page<MyBackTestResponse> getAllBackTestSortBySubscribe(Pageable pageable, Long userId) {
        List<Market> markets = queryFactory
                .selectFrom(market)
                .distinct()
                .join(market)
                .where(market.backTest.user.id.eq(userId))
                .orderBy(market.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<MyBackTestResponse> backTestSubscribeResponses = markets.stream()
                .map(
                        market1 -> MyBackTestResponse.builder()
                                .backTestId(market1.getBackTest().getId())
                                .name(market1.getBackTest().getName())
                                .startedDate(market1.getBackTest().getInvestStartDate())
                                .endDate(market1.getBackTest().getInvestEndDate())
                                .createdAt(market1.getBackTest().getCreatedAt())
                                .yearlyProfit(market1.getBackTest().getYearlyAverageProfit())
                                .unrealizedProfit(market1.getBackTest().getUnrealized_profit())
                                .mdd(market1.getBackTest().getGraph().getMdds().stream().map(mdd -> mdd.getMdd())
                                        .min(Float::compareTo).get())
                                .marketShared(market1.getBackTest().getMarketShared())
                                .initInvestmentFund(market1.getBackTest().getInitInvestmentFund())
                                .pieCharts(market1.getBackTest().getPieCharts().stream().map(
                                        investmentSectorsPieChart -> InvestmentSectorsPieChart.builder()
                                                .sector(investmentSectorsPieChart.getSector())
                                                .percentage(investmentSectorsPieChart.getPercentage())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()
                ).collect(Collectors.toList());
        long totalCount = queryFactory.selectFrom(market)
                .distinct()
                .join(market.backTest)
                .where(market.backTest.user.id.eq(userId))
                .fetchCount();

        return new PageImpl<>(backTestSubscribeResponses, pageable, totalCount);
    }

    @Override
    public Page<MyBackTestRecommendationResponse> getAllBackTestSortByRecommendation(Pageable pageable, Long userId) {
        List<Market> markets = queryFactory
                .selectFrom(market)
                .distinct()
                .join(market)
                .where(market.backTest.user.id.eq(userId))
                .orderBy(market.subscribeNum.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<MyBackTestRecommendationResponse> myBackTestRecommendationResponses =
                markets.stream().map(
                        market1 -> MyBackTestRecommendationResponse.builder()
                                .backTestId(market1.getBackTest().getId())
                                .name(market1.getBackTest().getName())
                                .startedDate(market1.getBackTest().getInvestStartDate())
                                .endDate(market1.getBackTest().getInvestEndDate())
                                .createdAt(market1.getBackTest().getCreatedAt())
                                .yearlyProfit(market1.getBackTest().getYearlyAverageProfit())
                                .unrealizedProfit(market1.getBackTest().getUnrealized_profit())
                                .mdd(market1.getBackTest().getGraph().getMdds().stream().map(mdd -> mdd.getMdd())
                                        .min(Float::compareTo).get())
                                .marketShared(market1.getBackTest().getMarketShared())
                                .subscribeNum(market1.getSubscribeNum())
                                .build()
                ).collect(Collectors.toList());

        long totalCount = queryFactory.selectFrom(market)
                .distinct()
                .join(market.backTest)
                .where(market.backTest.user.id.eq(userId))
                .fetchCount();

        return new PageImpl<>(myBackTestRecommendationResponses, pageable, totalCount);
    }
}
