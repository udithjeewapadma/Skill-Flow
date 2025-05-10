package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.FriendRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestWrapperDTO;
import com.example.skill_flow_paf.Service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping("/send")
    public void sendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.sendFriendRequest(requestDTO);
    }

    @PostMapping("/approve/{id}")
    public void approveRequest(@PathVariable Long id) {
        friendRequestService.approveFriendRequest(id);
    }

    @GetMapping
    public FriendRequestWrapperDTO getAllFriendRequests() {
        return friendRequestService.getAllFriendRequests();
    }
}
