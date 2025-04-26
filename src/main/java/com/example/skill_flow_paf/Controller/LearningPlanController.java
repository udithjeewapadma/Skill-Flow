package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.LearningPlanResponseDTO;
import com.example.skill_flow_paf.Models.LearningPlan;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/learning-plan")
public class LearningPlanController {

    @Autowired
    private LearningPlanService learningPlanService;


    @PostMapping
    public LearningPlanResponseDTO createLearningPlan(
            @RequestParam Long userId,  // User ID as query param
            @RequestBody CreateLearningPlanRequestDTO createLearningPlanRequestDTO) {  // Receive body in DTO

        LearningPlan learningPlan = learningPlanService.createLearningPlan(userId, createLearningPlanRequestDTO);

        // Mapping response DTO
        LearningPlanResponseDTO learningPlanResponseDTO = new LearningPlanResponseDTO();
        learningPlanResponseDTO.setId(learningPlan.getId());
        learningPlanResponseDTO.setTitle(learningPlan.getTitle());
        learningPlanResponseDTO.setDescription(learningPlan.getDescription());
        learningPlanResponseDTO.setProgress(learningPlan.getProgress());

        // Handle resources list correctly
        learningPlanResponseDTO.setResources(
                learningPlan.getResources() != null
                        ? List.of(learningPlan.getResources().split(","))
                        : Collections.emptyList()
        );

        learningPlanResponseDTO.setTimeLine(learningPlan.getTimeLine());

        // Set post IDs from learningPlan
        learningPlanResponseDTO.setPostIds(
                learningPlan.getPosts().stream()
                        .map(Post::getId)  // Convert list of posts to list of IDs
                        .toList()
        );

        return learningPlanResponseDTO;
    }

    @GetMapping("/{learning-plan-id}")
    private LearningPlanResponseDTO getById(@PathVariable("learning-plan-id") Long learningPlanId){
        return learningPlanService.findById(learningPlanId);
    }

}



