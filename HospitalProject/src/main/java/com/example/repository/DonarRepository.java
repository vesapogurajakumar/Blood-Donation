package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Donar;
import com.example.entity.Hospital;

public interface DonarRepository extends JpaRepository<Donar, Integer>{
//	List<Donar> findByCityAndArea(String city, String area);
	public List<Donar> getByCityAndArea(String city,String area);
	public List<Donar> getByArea(String area);
}
