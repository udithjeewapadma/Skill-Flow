package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Exception.PostNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.LearningPlan;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.LearningPlanRepository;
import com.example.skill_flow_paf.Repository.PostRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningPlanServiceImpl implements LearningPlanService {

    @Autowired
    private LearningPlanRepository learningPlanRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LearningPlan createLearningPlan(Long userId, CreateLearningPlanRequestDTO dto)
            throws PostNotFoundException, UserNotFoundExecption {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExecption("User ID " + userId + " not found"));


        List<Post> posts = postRepository.findAllById(dto.getPostIds());

        if (posts.size() != dto.getPostIds().size()) {
            throw new PostNotFoundException("Some post IDs are invalid.");
        }

        LearningPlan learningPlan = new LearningPlan();

        learningPlan.setTitle(dto.getTitle());
        learningPlan.setDescription(dto.getDescription());
        learningPlan.setResources(String.join(",", dto.getResources()));
        learningPlan.setTimeLine(dto.getTimeLine());
        learningPlan.setProgress(dto.getProgress());
        learningPlan.setPosts(posts);

        return learningPlanRepository.save(learningPlan);
    }




}
