package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Exception.PostNotFoundException;
import com.example.skill_flow_paf.Models.LearningPlan;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Repository.LearningPlanRepository;
import com.example.skill_flow_paf.Repository.PostRepository;
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

    @Override
    public LearningPlan createLearningPlan(CreateLearningPlanRequestDTO createLearningPlanRequestDTO) throws PostNotFoundException {

        List<Post> posts = postRepository.findAllById(createLearningPlanRequestDTO.getPostIds());

        if (posts.isEmpty() || posts.size() != createLearningPlanRequestDTO.getPostIds().size()) {
            throw new PostNotFoundException("Some post IDs are invalid.");
        }

        LearningPlan learningPlan = new LearningPlan();
        learningPlan.setTitle(createLearningPlanRequestDTO.getTitle());
        learningPlan.setDescription(createLearningPlanRequestDTO.getDescription());
        learningPlan.setResources(String.join(",", createLearningPlanRequestDTO.getResources())); // Convert List to String
        learningPlan.setTimeLine(createLearningPlanRequestDTO.getTimeLine());
        learningPlan.setProgress(createLearningPlanRequestDTO.getProgress());
        learningPlan.setPosts(posts);

        return learningPlanRepository.save(learningPlan);
    }

}
