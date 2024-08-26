package com.quantweb.springserver.domain.back_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;

import java.util.List;
import java.util.Optional;

public interface BackTestRepository extends JpaRepository<BackTest, Long>, BackTestRepositoryCustom {

    Boolean existsByName(String name);
    Optional<List<BackTest>> findAllByUserId(Long userId);

}
