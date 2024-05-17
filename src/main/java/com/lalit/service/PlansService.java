package com.lalit.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lalit.entity.PlansApp;

@Component
public interface PlansService {
	public List<PlansApp> getPlans();
	public void savePlans(PlansApp object) ;

}
