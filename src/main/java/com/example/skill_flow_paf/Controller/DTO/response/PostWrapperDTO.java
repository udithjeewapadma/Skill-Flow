package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostWrapperDTO {

    private int totalPosts;
    private List<PostResponseDTO> posts;
}
