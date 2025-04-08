package com.example.skill_flow_paf.Controller.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Size(max = 255, message = "About section cannot exceed 255 characters")
    private String about;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;

    @Size(max = 255, message = "Qualifications section cannot exceed 255 characters")
    private String qualifications;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
