package com.example.dao;

import java.util.List;

import com.example.entity.Hospital;

public interface HospitalDao {
public List<Hospital> getByCityAndArea(String city,String area);
public List<Hospital> getByArea(String area);
}
