package com.example.skill_flow_paf.Repository;

import com.example.skill_flow_paf.Models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f FROM Friend f WHERE f.sender.id = :userId OR f.receiver.id = :userId")
    List<Friend> findAllFriendsByUserId(@Param("userId") Long userId);
}
