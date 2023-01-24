package com.Department.Status;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DepartmentStatus{

	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	DELETED("DELETED");

	@Getter
	private String value;

	public static List<String> getAllValues() {
		return List.of(DepartmentStatus.values()).stream().map(data -> data.value).collect(Collectors.toList());
	}
}