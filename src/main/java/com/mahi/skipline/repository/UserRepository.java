package com.mahi.skipline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mahi.skipline.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

User findByEmail(String email);

}