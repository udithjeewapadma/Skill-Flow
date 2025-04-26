package com.example.skill_flow_paf.Controller.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateLearningPlanRequestDTO {

    private String title;
    private String description;
    private List<String> resources;
    private String timeLine;
    private List<Long> postIds;
    private int progress;

}
