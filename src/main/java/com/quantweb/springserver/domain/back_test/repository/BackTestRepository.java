package com.quantweb.springserver.domain.back_test.repository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackTestRepository extends JpaRepository<BackTest, Long> {
}
