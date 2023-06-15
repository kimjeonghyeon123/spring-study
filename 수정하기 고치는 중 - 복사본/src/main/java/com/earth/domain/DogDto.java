package com.earth.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DogDto {
	private String dog_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dog_birth;
	private String dog_gender;
	private String dog_nutd;
	private String dog_breed;
	private String dog_regnum;
	private String dog_address;
	private String dog_feature;
	private String user_email;
	
	@Override
	public String toString() {
		return "DogVo [dog_name=" + dog_name + ", dog_birth=" + dog_birth + ", dog_gender=" + dog_gender + ", dog_nutd="
				+ dog_nutd + ", dog_breed=" + dog_breed + ", dog_regnum=" + dog_regnum + ", dog_address=" + dog_address
				+ ", dog_feature=" + dog_feature + "]";
	}
	
	
}
