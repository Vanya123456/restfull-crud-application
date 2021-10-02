package com.buyukli.ivan.restfullcrudapplication.repository;

import com.buyukli.ivan.restfullcrudapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
