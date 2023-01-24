package com.Department.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Department.Service.DepartmentService;
import com.Department.common.Request;
import com.Department.common.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Response>> getAll(){
		List<Response> departments= departmentService.getAll();
		if(departments.size()>0)return ResponseEntity.ok(departments);
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/allTemp")
	public ResponseEntity<List<Response>> getAllTemp(){
		List<Response> departments= departmentService.getAllTemp();
		if(departments.size()>0)return ResponseEntity.ok(departments);
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addDept(@Valid @RequestBody Request department) {
		if(department.getDeptName() != null || department.getDeptName().isBlank()) return ResponseEntity.ok(departmentService.addDept(department));
		else return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getDept(@PathVariable("id") Long id) {
		Optional<Response> departments= (departmentService.getdept(id));
		if (departments.isPresent()) return ResponseEntity.ok(departments.get());
		else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateDept(@Valid @RequestBody Request department) {
		if(department.getDeptName() != null || department.getDeptName().length() > 0 ) return ResponseEntity.ok(departmentService.updateDept(department));
		else return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteDept(@PathVariable Long id) {
		Optional<Response> department = departmentService.deleteDept(id);
		if (department.isPresent()) return ResponseEntity.ok(department.get());
		else return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteTemp/{id}")
	public ResponseEntity<Response> deleteTempDept(@PathVariable Long id) {
		Optional<Response> department = departmentService.deleteTempDept(id);
		if (department.isPresent()) return ResponseEntity.ok(department.get());
		else return ResponseEntity.noContent().build();
	}
}
