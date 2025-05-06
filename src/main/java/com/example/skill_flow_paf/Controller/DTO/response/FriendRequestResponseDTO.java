package com.example.skill_flow_paf.Controller.DTO.response;
import com.example.skill_flow_paf.Models.RequestStatus;
import lombok.Data;

@Data
public class FriendRequestResponseDTO {

    private Long requestId;
    private String receiverName;
    private RequestStatus requestStatus;
}
