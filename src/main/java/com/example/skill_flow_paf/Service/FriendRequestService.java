package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.FriendRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestResponseDTO;
import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestWrapperDTO;

public interface FriendRequestService {

    void sendFriendRequest(FriendRequestDTO requestDTO);
    void approveFriendRequest(Long requestId);
    FriendRequestWrapperDTO getAllFriendRequests();
}
