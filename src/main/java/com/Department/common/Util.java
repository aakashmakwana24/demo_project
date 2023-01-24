package com.Department.common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Department.Dao.Department;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Util {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Department requestToPojo(Request request) {
		log.info("Converting to Entity");
		return modelMapper.map(request, Department.class);
	}
	
	public Response pojoToResponse(Department department) {
		log.info("Converting to response");
		Response response = new Response();
		response.setDeptName(department.getDeptName());
		return response;
	}
}
