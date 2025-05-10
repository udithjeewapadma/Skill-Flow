package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/{userId}")
    public List<User> getFriends(@PathVariable Long userId) {
        return friendService.getFriends(userId);
    }
}