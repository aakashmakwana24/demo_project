package com.Department.common;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	
	@NotNull
	@Size(min = 3, message = "The name has to be more than 2 characters")
	private String deptName;

}
