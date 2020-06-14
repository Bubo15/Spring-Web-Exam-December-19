package com.example.clothesshop.data.repositories;

import com.example.clothesshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String name);
}
