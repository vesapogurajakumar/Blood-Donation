package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Hospital;
import com.example.repository.HospitalRepository;

@Service
public class HospitalDaoImpl implements HospitalDao{
	
	@Autowired
	HospitalRepository repository;

	@Override
	public List<Hospital> getByCityAndArea(String city, String area) {
		return repository.findByCityAndArea(city, area);
		
	}

	@Override
	public List<Hospital> getByArea(String area) {
		return repository.findByArea(area);
		
	}
}
