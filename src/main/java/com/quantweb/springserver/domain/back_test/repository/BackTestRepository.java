package com.quantweb.springserver.domain.back_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;

public interface BackTestRepository extends JpaRepository<BackTest, Long> {

}
