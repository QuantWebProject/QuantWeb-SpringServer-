package com.quantweb.springserver.domain.account.entity;

import com.quantweb.springserver.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.user.id = :userId")
    Optional<Account> findByUserId(Long userId);

    boolean existsByUserAndType(User user, AccountType accountType);
}
