package com.quantweb.springserver.domain.back_test.repository;


import com.quantweb.springserver.domain.back_test.entity.BackTest;
import com.quantweb.springserver.domain.dashboard.dto.BackTestSubscribeResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestRecommendationResponse;
import com.quantweb.springserver.domain.dashboard.dto.MyBackTestResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackTestRepositoryCustom {
    Page<MyBackTestResponse> getAllBackTestSortByLatest(Pageable pageable, Long userId,String type);
    Page<MyBackTestResponse> getAllBackTestSortBySubscribe(Pageable pageable, Long userId);
    Page<MyBackTestRecommendationResponse> getAllBackTestSortByRecommendation(Pageable pageable, Long userId);
}
