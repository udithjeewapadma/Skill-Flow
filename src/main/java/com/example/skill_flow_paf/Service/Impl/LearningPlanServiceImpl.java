package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.LearningPlanResponseDTO;
import com.example.skill_flow_paf.Exception.LearningPlanNotFoundException;
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

import java.util.Collections;
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

    @Override
    public LearningPlanResponseDTO findById(Long id) throws LearningPlanNotFoundException {

        LearningPlan learningPlan = learningPlanRepository.findById(id)
                .orElseThrow(() -> new LearningPlanNotFoundException("Learning plan not found with id: " + id));

        LearningPlanResponseDTO responseDTO = new LearningPlanResponseDTO();
        responseDTO.setId(learningPlan.getId());
        responseDTO.setTitle(learningPlan.getTitle());
        responseDTO.setDescription(learningPlan.getDescription());
        responseDTO.setResources(
                learningPlan.getResources() != null ? List.of(learningPlan.getResources().split(",")) : Collections.emptyList()
        );
        responseDTO.setTimeLine(learningPlan.getTimeLine());
        responseDTO.setProgress(learningPlan.getProgress());

        responseDTO.setPostIds(learningPlan.getPosts().stream()
                .map(post -> post.getId())
                .toList());

        return responseDTO;
    }


}
