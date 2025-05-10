package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.FriendRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestResponseDTO;
import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestWrapperDTO;
import com.example.skill_flow_paf.Models.Friend;
import com.example.skill_flow_paf.Models.FriendRequest;
import com.example.skill_flow_paf.Models.RequestStatus;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.FriendRepository;
import com.example.skill_flow_paf.Repository.FriendRequestRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void sendFriendRequest(FriendRequestDTO requestDTO) {
        User sender = userRepository.findById(requestDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(requestDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setRequestStatus(RequestStatus.PENDING);

        friendRequestRepository.save(request);
    }

    @Transactional
    @Override
    public void approveFriendRequest(Long requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        request.setRequestStatus(RequestStatus.APPROVED);
        friendRequestRepository.save(request);

        Friend friend = new Friend();
        friend.setSender(request.getSender());
        friend.setReceiver(request.getReceiver());
        friendRepository.save(friend);
    }

    @Override
    public FriendRequestWrapperDTO getAllFriendRequests() {
        List<FriendRequest> requests = friendRequestRepository.findAll();

        List<FriendRequestResponseDTO> requestDTOs = requests.stream().map(request -> {
            FriendRequestResponseDTO dto = new FriendRequestResponseDTO();
            dto.setRequestId(request.getId());
            dto.setReceiverName(request.getReceiver().getUsername());
            dto.setRequestStatus(request.getRequestStatus()); // Include status
            return dto;
        }).collect(Collectors.toList());

        FriendRequestWrapperDTO wrapper = new FriendRequestWrapperDTO();
        wrapper.setFriendRequests(requestDTOs);
        wrapper.setTotalRequests(requestDTOs.size());

        return wrapper;
    }
}
