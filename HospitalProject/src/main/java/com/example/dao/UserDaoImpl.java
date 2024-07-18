package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Donar;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao{
	
	@Autowired
	UserRepository repository;

	@Override
	public User userRegistrtaion(User user) {
		return repository.save(user);
	}

	@Override
	public User userLogin(String emailormobile, String password) {
		return repository.findByEmailOrMobilenumberAndPassword(emailormobile, emailormobile, password);
	}
	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	

}
