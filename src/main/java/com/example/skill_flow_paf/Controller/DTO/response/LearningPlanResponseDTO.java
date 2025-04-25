package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class LearningPlanResponseDTO {

    private Long id;
    private String title;
    private String description;
    private List<String> resources;
    private String timeLine;
    private List<Long> postIds;
    private int progress;
}
