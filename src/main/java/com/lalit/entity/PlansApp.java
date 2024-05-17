package com.lalit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Plans")
public class PlansApp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer Id;
	
	public Integer getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String plansName;

	public String getPlansName() {
		return plansName;
	}

	public void setPlansName(String plansName) {
		this.plansName = plansName;
	}

}
