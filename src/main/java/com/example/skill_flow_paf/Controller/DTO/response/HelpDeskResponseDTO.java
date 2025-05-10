package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;

@Data
public class HelpDeskResponseDTO {
    private Long id;
    private String question;
    private Long userId;
    private String username;
}
