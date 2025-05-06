package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Models.Friend;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.FriendRepository;
import com.example.skill_flow_paf.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Override
    public List<User> getFriends(Long userId) {
        List<Friend> friends = friendRepository.findAllFriendsByUserId(userId);
        List<User> friendList = new ArrayList<>();

        for (Friend friend : friends) {
            if (friend.getSender().getId().equals(userId)) {
                friendList.add(friend.getReceiver());
            } else {
                friendList.add(friend.getSender());
            }
        }

        return friendList;
    }
}
