package com.Department.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Department.Dao.Department;
import com.Department.Dao.DepartmentRepo;
import com.Department.Exceptions.ResourceNotFoundException;
import com.Department.Status.DepartmentStatus;
import com.Department.common.Request;
import com.Department.common.Response;
import com.Department.common.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private Util util;
	
	public List<Response> getAll() {
		
		return departmentRepo.findAll().stream().map(department -> this.util.pojoToResponse(department)).collect(Collectors.toList());
	}
	
	public List<Response> getAllTemp() {
		return departmentRepo.findByValue(DepartmentStatus.ACTIVE).stream().map(department -> this.util.pojoToResponse(department)).collect(Collectors.toList());
	}

	
	public Response addDept(Request request) {
		Department department = util.requestToPojo(request);
		return util.pojoToResponse(departmentRepo.save(department));
	}
	
	public Optional<Response> getdept(Long id) {
		Optional<Department> data = departmentRepo.findById(id);
		if (data.isPresent()) return Optional.of(this.util.pojoToResponse(data.get()));
		else throw new ResourceNotFoundException("Department", "id", id);
	}

	public Response updateDept(Request request) {
		Department department = util.requestToPojo(request);
		return util.pojoToResponse(departmentRepo.save(department));
	}
	
	public Optional<Response> deleteDept(Long Id) {
		Optional<Department> data = departmentRepo.findById(Id);
		if(data.isPresent()) {
			departmentRepo.delete(data.get());
			return Optional.of(this.util.pojoToResponse(data.get()));
		}
		return Optional.empty();
	}
	
	public Optional<Response> deleteTempDept(Long Id) {
		Optional<Department> data = departmentRepo.findById(Id);
		if(data.isPresent()	) {
			data.get().setValue(DepartmentStatus.INACTIVE);
			return Optional.of( this.util.pojoToResponse(departmentRepo.save(data.get())));
		}
		else throw new ResourceNotFoundException("Department", "id", Id);
	}
}
