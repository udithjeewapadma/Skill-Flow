package com.example.skill_flow_paf.Controller.DTO.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateHelpDeskRequestDTO {

    @NotBlank(message = "Question must not be empty")
    @Size(min = 10, max = 1000, message = "Question must be between 5 and 1000 characters")
    private String question;
}
