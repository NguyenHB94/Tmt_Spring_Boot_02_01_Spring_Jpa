package com.tmt.repository;

import com.tmt.enitty.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findById(Integer accoutId);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUserName(String userName);
}
