package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateLearningPlanRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.LearningPlanResponseDTO;
import com.example.skill_flow_paf.Models.LearningPlan;

import java.util.List;

public interface LearningPlanService {
    LearningPlan createLearningPlan(Long userId, CreateLearningPlanRequestDTO createLearningPlanRequestDTO);

    LearningPlanResponseDTO findById(Long id);

    List<LearningPlanResponseDTO> findAll();

    void deleteById(Long id);

}
