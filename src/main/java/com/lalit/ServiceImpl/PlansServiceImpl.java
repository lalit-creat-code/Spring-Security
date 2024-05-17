package com.lalit.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lalit.Repo.PlansRepo;
import com.lalit.entity.PlansApp;
import com.lalit.service.PlansService;

@Service
public class PlansServiceImpl implements PlansService {
	@Autowired
	private PlansRepo repo;
	@Override
	public List<PlansApp> getPlans(){
		List<PlansApp>list=repo.findAll();
		
		  return list;
	}
	
	@Override
	public void savePlans(PlansApp object) {
		repo.save(object);
		
	}

}