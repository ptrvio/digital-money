package com.digitalhouse.users_service.repositories;

import com.digitalhouse.users_service.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
