package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;


@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String qualifications;
    private String about;
}
