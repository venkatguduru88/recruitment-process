package com.recruitment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyJobRequest {

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Valid email is required")
	@NotBlank(message = "Email is required")
	private String email;

}
