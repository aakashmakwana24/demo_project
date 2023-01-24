package com.Department.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Department.Status.DepartmentStatus;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{
	public List<Department> findByValue(DepartmentStatus departmentStatus);
}
