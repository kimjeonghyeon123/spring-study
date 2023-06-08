package com.earth.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PetssiterDto {
	private String name;
	private Date birth;
	private String gener;
	private String phone;
	private String adress;
	private String residence;
	private String smoke;
	private String pet;
	private String exp;
	private String etc;
	
	@Override
	public String toString() {
		return "PetssiterDto [name=" + name + ", birth=" + birth + ", gener=" + gener + ", phone=" + phone + ", adress="
				+ adress + ", residence=" + residence + ", smoke=" + smoke + ", pet=" + pet + ", exp=" + exp + ", etc="
				+ etc + "]";
	}
	
	
}
