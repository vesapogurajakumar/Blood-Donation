package com.example.dao;

import java.util.List;

import com.example.entity.Donar;
import com.example.entity.User;

public interface UserDao {
	public User userRegistrtaion(User user);
	public User userLogin(String emailormobile,String password);
	List<User> getAllUsers();
	User findByEmail(String email);
	

}
