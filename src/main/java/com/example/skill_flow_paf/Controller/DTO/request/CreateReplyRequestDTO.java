package com.example.skill_flow_paf.Controller.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateReplyRequestDTO {

    @NotBlank(message = "Reply text must not be empty")
    @Size(min = 2, max = 1000, message = "Reply must be between 2 and 1000 characters")
    private String replyText;
}
