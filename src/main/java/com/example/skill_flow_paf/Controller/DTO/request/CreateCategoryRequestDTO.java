package com.example.skill_flow_paf.Controller.DTO.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCategoryRequestDTO {

    @NotBlank(message = "Category name must not be blank")
    @Pattern(regexp = "^[^\\d]*$", message = "Category name must not contain numbers")
    private String categoryName;

}
