package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> imageUrls;
    private Long userId;
    private Long categoryId;
}
