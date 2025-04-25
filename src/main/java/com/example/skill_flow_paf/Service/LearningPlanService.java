package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Models.LearningPlan;

public interface LearningPlanService {
    LearningPlan createLearningPlan(CreateLearningPlanRequestDTO createLearningPlanRequestDTO);
}
