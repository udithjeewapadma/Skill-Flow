package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Controller.DTO.response.FriendRequestResponseDTO;
import com.example.skill_flow_paf.Models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
