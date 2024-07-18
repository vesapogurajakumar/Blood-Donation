package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.HospitalDao;
import com.example.entity.Donar;
import com.example.entity.Hospital;
import com.example.listofdifferentcities.HyderabadList;

@Controller
public class TestController {
	
	@Autowired
	HospitalDao dao;
	
	@RequestMapping("/oprionpage")
	public String optionPage() {
		return "OptionPage";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "MainPage";
	}
	
	@RequestMapping("/donatepage")
	public String donatePage() {
		return "DonatePage";
	}
	
	@GetMapping("/hospitalByCityAndname")
	public String getHospitalByCityArea(String city,String area,String userarea,Model model) {
		if(city.equals("hyderabad")) {
			List<Hospital> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				model.addAttribute("bycityArea", byCityAndArea);
				return "ListOfHospitalsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else if(city.equals("warangal")) {
			List<Hospital> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				model.addAttribute("bycityArea", byCityAndArea);
				return "ListOfHospitalsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else {
			model.addAttribute("donarmessage", "city does not exist");
			return "DonatePage";
		}
	}
	public String nearest(List<Hospital> byCityAndArea,String city,String area,String userarea,Model model) {
		if(!byCityAndArea.isEmpty()) {
			model.addAttribute("userarea", area.toUpperCase());
			model.addAttribute("bycityArea", byCityAndArea);
			return "ListOfHospitalsByCityAndArea";
		}
		else {
			NearestArea neareArea=new NearestArea();
			String next=null;
			String previous=null;
			if(city.equals("hyderabad")) {
				HyderabadList hyd=new HyderabadList();
				LinkedList<String> li=hyd.hyderabadAreas();
				next = neareArea.next(area,li);
				previous = neareArea.previous(area,li);
			}
			else if(city.equals("warangal")) {
				HyderabadList hyd=new HyderabadList();
				LinkedList<String> li=hyd.warangalAreas();
				next = neareArea.next(area,li);
				previous = neareArea.previous(area,li);
			}
			else if(city.equals("Karimnagar")) {
				HyderabadList hyd=new HyderabadList();
				LinkedList<String> li=hyd.karimnagarAreas();
				next = neareArea.next(area,li);
				previous = neareArea.previous(area,li);
			}
			else if(city.equals("Nizamabad")) {
				HyderabadList hyd=new HyderabadList();
				LinkedList<String> li=hyd.nizamabadAreas();
				next = neareArea.next(area,li);
				previous = neareArea.previous(area,li);
			}
			System.out.println(next);
			System.out.println(previous);
			List<Hospital> hospitals=new LinkedList<>();
			if(next!=null) {
				hospitals.addAll(dao.getByArea(next));
			}
			if(previous!=null) {
				hospitals.addAll(dao.getByArea(previous));
			}
			if((next==null&&previous==null)||hospitals.size()==0) {
				model.addAttribute("message","City Or area not present");
				return "DonatePage";
			}
			model.addAttribute("bycityArea", hospitals);
			System.out.println(hospitals.size());
			
			model.addAttribute("userar2",area);
			
			if(userarea!=null) {
				model.addAttribute("userarea", userarea);
			}
			else {
				model.addAttribute("userarea","secunderabad");
			}
			return "NearestLoc";
		}
	}
}
