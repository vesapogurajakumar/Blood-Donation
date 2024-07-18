package com.example.controller;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.DonarDao;
import com.example.dao.DonarDaoImpl;
import com.example.entity.Donar;
import com.example.entity.Hospital;
import com.example.listofdifferentcities.HyderabadList;

import jakarta.servlet.http.HttpSession;

@Controller
public class DonarController {

	
	@Autowired
	DonarDao dao;
	
	@RequestMapping("/donorpage")
	public String donarsPage() {
		return "DonarPage";
	}
	@RequestMapping("/donorregistrationpage")
	public String donarRegistrationPage() {
		getAllDonorsIntoMap();
		return "DonorRegistration";
	}
	String username;
	@RequestMapping("/mainpage2")
	public String mainPage2(Model model) {
		model.addAttribute("loginname",username);
		model.addAttribute("donarregst", "THANK YOU FOR BEING A DONOR");
		return "MainPage2";
	}
	
	@RequestMapping("/donorregistration")
	public String regsiterDonar(Donar donar,Model model) {
		Map<String, String> allDonorsIntoMap = getAllDonorsIntoMap();
		if((allDonorsIntoMap.containsKey(donar.getEmailid())||allDonorsIntoMap.containsValue(donar.getMobilenumber()))) {
			model.addAttribute("donarregst", "You are  allready Donar");
			return "DonorRegistration";
		}
		Donar donorRegistration = dao.donorRegistration(donar);
		if(donorRegistration!=null) {
			
//			model.addAttribute("loginname", donar.getDonarname().charAt(0));
			username=donorRegistration.getDonarname();
			return "redirect:/mainpage2";
		}
		else {
			model.addAttribute("donorregst", "You already Donor");
			return "DonorRegistration";
		}
	}
	@GetMapping("/donarhospitalByCityAndname")
	public String getHospitalByCityArea(String city,String area,String userarea,Model model) {
		if(city.equals("hyderabad")) {
			List<Donar> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				
				model.addAttribute("donarbycityArea", byCityAndArea);
				return "ListOfDonarsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else if(city.equals("warangal")) {
			List<Donar> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				model.addAttribute("donarbycityArea", byCityAndArea);
				return "ListOfDonarsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else if(city.equals("Nizamabad")) {
			List<Donar> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				model.addAttribute("donarbycityArea", byCityAndArea);
				return "ListOfDonarsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else if(city.equals("Karimnagar")) {
			List<Donar> byCityAndArea = dao.getByCityAndArea(city, area);
			if(!byCityAndArea.isEmpty()) {
				model.addAttribute("userarea", area.toUpperCase());
				model.addAttribute("donarbycityArea", byCityAndArea);
				return "ListOfDonarsByCityAndArea";
			}
			return nearest(byCityAndArea,city,area,userarea,model);
		}
		else {
			model.addAttribute("donarmessage", "city does not exist");
			return "DonarPage";
		}
		
	}
	public String nearest(List<Donar> byCityAndArea,String city,String area,String userarea,Model model) {
		if(!byCityAndArea.isEmpty()) {
			model.addAttribute("userarea", area.toUpperCase());
			model.addAttribute("donarbycityArea", byCityAndArea);
			return "ListOfDonarsByCityAndArea";
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
			System.out.println(next);
			System.out.println(previous);
			List<Donar> donar=new LinkedList<>();
			
			if(next!=null) {
				donar.addAll(dao.getByArea(next));
			}
			if(previous!=null) {
				donar.addAll(dao.getByArea(previous));
			}
			if((next==null&&previous==null)||donar.size()==0) {
				model.addAttribute("donarmessage","City Or area not present");
				return "DonarPage";
			}
			
			
			model.addAttribute("donarbycityArea", donar);
			System.out.println(donar.size());
			
			model.addAttribute("userar", area);
			
			if(userarea!=null) {
				model.addAttribute("userarea2", userarea);
			}
			else {
				model.addAttribute("userarea2", "secunderabad");
			}
			return "DonarNearest";
		}
		
	}
	public Map<String, String> getAllDonorsIntoMap() {
	    List<Donar> allDonars = dao.getAllDonars();
	    Map<String, String> map = new HashMap<>();
	    for (Donar donar : allDonars) {
	        map.put(donar.getEmailid(), donar.getMobilenumber());
	    }
	    return map;
	}
}
