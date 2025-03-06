package com.digitalhouse.users_service.repositories;

import com.digitalhouse.users_service.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
