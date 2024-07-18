package com.example.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class MapOfCities {
	private void allCities() {
		Map<String,LinkedList<String>> map= new HashMap<>();
		LinkedList<String> link = new LinkedList<>();
        link.add("miyapur");
        link.add("jntu");
        link.add("kphb");
        link.add("kukatpalli");
        link.add("balanagar");
        link.add("musapet");
        link.add("bharath nagar");    
        link.add("erragadda"); 
        link.add("esi hospital");
        link.add("sr nagar");
        link.add("ameerpet");
        link.add("punjagutta");
        link.add("irrummanjil");
		map.put("hyderabad",link);
		LinkedList<String> Warangal=new LinkedList<>();

	}
}
