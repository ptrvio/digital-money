package com.digitalhouse.users_service.repositories;

import com.digitalhouse.users_service.model.entities.Account;
import com.digitalhouse.users_service.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUserId(Long use_id);
}
