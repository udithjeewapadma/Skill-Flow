package com.example.skill_flow_paf.Controller.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateHelpDeskRequestDTO {

    @NotBlank(message = "Question must not be empty")
    @Size(min = 5, max = 1000, message = "Question must be between 5 and 1000 characters")
    private String question;
}
