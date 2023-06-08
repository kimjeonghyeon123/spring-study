package com.earth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earth.mapper.PetsitterMapper;

@Service
public class PetsitterServiceImpl implements PetsitterService {

	@Autowired
	PetsitterMapper petMapper;
}
