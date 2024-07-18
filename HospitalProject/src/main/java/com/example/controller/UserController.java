package com.example.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dao.DonarDao;
import com.example.dao.DonarDaoImpl;
import com.example.dao.UserDao;
import com.example.entity.Donar;
import com.example.entity.Hospital;
import com.example.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserDao dao;
	
	@Autowired
	DonarDao daoD;
	
	@GetMapping("/userloginn")
	public String userlogin() {
		return "UserLogin";
	}
	
	@GetMapping("/userregistrationn")
	public String userregistration() {
		return "UserRegistration";
	}
	
	@GetMapping("/userregistration")
	public String userRegistration(User user,Model model) {
		
		Map<String,String> map=getAllUsersIntoMap();
		if((map.containsKey(user.getEmail()))||map.containsValue(user.getMobilenumber())) {
			model.addAttribute("message","This Email OR Mobile number is already Registered");
			return "UserRegistration";
		}
		User userRegistrtaion = dao.userRegistrtaion(user);
		if(userRegistrtaion!=null) {
			return "redirect:/userloginn";
		}
		else {
			model.addAttribute("message", "Enter correct Credentials");
			return "UserRegistration";
		}
	}
	HttpSession session;
	@GetMapping("/userlogin")
	public String userLogin(String emailormobile,String password,Model model) {
		Map<String,String> donormap=getAllDonorsIntoMap();
		User userLogin = dao.userLogin(emailormobile, password);
		if(userLogin!=null&&(donormap.containsKey(emailormobile))||donormap.containsValue(emailormobile)) {
			model.addAttribute("donarregst", "Thank you for donating blood; your generosity saves lives.");
			model.addAttribute("loginname",dao.findByEmail(emailormobile).getUsername().toUpperCase());
			return "MainPage3";
		}
		
		if(userLogin!=null) {
			model.addAttribute("loginname",userLogin.getUsername().toUpperCase().charAt(0));
			model.addAttribute("lusername", userLogin.getUsername());
			model.addAttribute("luserlocation", userLogin.getCurrentlocation());
			System.out.println(userLogin.getCurrentlocation());
			model.addAttribute("lusermobilenumber", userLogin.getMobilenumber());
			model.addAttribute("luseremail", userLogin.getEmail());
			return "MainPage2";
		}
		else {
			model.addAttribute("loginmessage", "Incurrect Credentials");
			return "UserLogin";
		}
	}
	
	public Map<String, String> getAllUsersIntoMap() {
	    List<User> allUsers = dao.getAllUsers();
	    Map<String, String> map = new HashMap<>();
	    for (User user : allUsers) {
	        map.put(user.getEmail(),user.getMobilenumber());
	    }
	    return map;
	}
	public Map<String, String> getAllDonorsIntoMap() {
	    List<Donar> allDonars = daoD.getAllDonars();
	    Map<String, String> map = new HashMap<>();
	    for (Donar donar : allDonars) {
	        map.put(donar.getEmailid(), donar.getMobilenumber());
	    }
	    return map;
	}
}
