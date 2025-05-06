package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class FriendRequestWrapperDTO {
    private List<FriendRequestResponseDTO> friendRequests;
    private int totalRequests;
}
