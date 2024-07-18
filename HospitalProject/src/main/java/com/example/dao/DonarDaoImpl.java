package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Donar;
import com.example.entity.Hospital;
import com.example.repository.DonarRepository;


@Service
public class DonarDaoImpl implements DonarDao{
	
	@Autowired
	DonarRepository repository;

	@Override
	public List<Donar> getByCityAndArea(String city, String area) {
		return repository.getByCityAndArea(city, area);
	}

	@Override
	public List<Donar> getByArea(String area) {
		return repository.getByArea(area);
	}

	@Override
	public Donar donorRegistration(Donar donor) {
		return repository.save(donor);
	}

	@Override
	public List<Donar> getAllDonars() {
		return repository.findAll();
	}

}
