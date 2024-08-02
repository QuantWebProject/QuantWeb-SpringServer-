package com.quantweb.springserver.domain.back_test.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.quantweb.springserver.domain.back_test.entity.BackTest;

import java.util.List;
import java.util.Optional;

public interface BackTestRepository extends JpaRepository<BackTest, Long> {

    Boolean existsByName(String name);
=======
import com.quantweb.springserver.domain.back_test.entity.BackTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackTestRepository extends JpaRepository<BackTest, Long> {

>>>>>>> e46edb7 (feat: 매매내역 조회하기)

    Optional<List<BackTest>> findAllByUserId(Long userId);

}