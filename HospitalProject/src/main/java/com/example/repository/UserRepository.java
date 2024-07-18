package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmailOrMobilenumberAndPassword(String email, String mobilenumber, String password);
	User findByEmail(String email);

}
