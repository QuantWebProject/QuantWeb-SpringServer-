package com.quantweb.springserver.domain.back_test.repository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BackTestRepository extends JpaRepository<BackTest, Long> {
}
