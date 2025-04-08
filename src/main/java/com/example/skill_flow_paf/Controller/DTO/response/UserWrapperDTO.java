package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserWrapperDTO {

    private int totalUsers;
    private List<UserResponseDTO> users;
}
