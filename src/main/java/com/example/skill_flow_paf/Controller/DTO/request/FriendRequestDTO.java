package com.example.skill_flow_paf.Controller.DTO.request;

import com.example.skill_flow_paf.Models.RequestStatus;
import lombok.Data;

@Data
public class FriendRequestDTO {
    private Long senderId;
    private Long receiverId;
    private RequestStatus requestStatus;
}
