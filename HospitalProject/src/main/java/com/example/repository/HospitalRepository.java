package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Hospital;
import java.util.List;


public interface HospitalRepository extends JpaRepository<Hospital, Integer>{
	
	List<Hospital> findByCityAndArea(String city, String area);
	List<Hospital> findByArea(String area);
	List<Hospital> getByArea(String area);
}
