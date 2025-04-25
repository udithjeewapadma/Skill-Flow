package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.LearningPlanResponseDTO;
import com.example.skill_flow_paf.Models.LearningPlan;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/learning-plan")
public class LearningPlanController {

    @Autowired
    private LearningPlanService learningPlanService;


    @PostMapping
    public LearningPlanResponseDTO createLearningPlan(@RequestBody CreateLearningPlanRequestDTO createLearningPlanRequestDTO) {
        LearningPlan learningPlan = learningPlanService.createLearningPlan(createLearningPlanRequestDTO);

        LearningPlanResponseDTO learningPlanResponseDTO = new LearningPlanResponseDTO();
        learningPlanResponseDTO.setId(learningPlan.getId());
        learningPlanResponseDTO.setTitle(learningPlan.getTitle());
        learningPlanResponseDTO.setDescription(learningPlan.getDescription());

        learningPlanResponseDTO.setResources(
                learningPlan.getResources() != null ? List.of(learningPlan.getResources().split(",")) : Collections.emptyList()
        );

        learningPlanResponseDTO.setTimeLine(learningPlan.getTimeLine());

        // Correctly extract post IDs
        learningPlanResponseDTO.setPostIds(learningPlan.getPosts().stream()
                .map(Post::getId)
                .toList());

        return learningPlanResponseDTO;
    }

}
