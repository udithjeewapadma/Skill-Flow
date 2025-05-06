package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Models.User;

import java.util.List;

public interface FriendService {
    List<User> getFriends(Long userId);

}
