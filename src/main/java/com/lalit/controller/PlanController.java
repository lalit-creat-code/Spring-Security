package com.lalit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lalit.ServiceImpl.PlansServiceImpl;
import com.lalit.entity.PlansApp;

@RestController
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	private PlansServiceImpl service;
	
	@GetMapping("/getPlan")
	public List< PlansApp> getPlans(@RequestBody PlansApp object) {
	List	<PlansApp> app=service.getPlans();
	         return app;
		
		}
	@PostMapping("/savePlan")
	public String savePlan(@RequestBody PlansApp object) {
		service.savePlans(object);
		return "new plans updated";
	}
	

}
