package com.example.dao;

import java.util.List;

import com.example.entity.Donar;
import com.example.entity.Hospital;

public interface DonarDao {
	public List<Donar> getByCityAndArea(String city,String area);
	public List<Donar> getByArea(String area);
	public Donar donorRegistration(Donar donor);
	List<Donar> getAllDonars();
}
