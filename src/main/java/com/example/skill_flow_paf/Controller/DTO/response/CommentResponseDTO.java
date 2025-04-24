package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private Long userId;
    private Long postId;
    private int likedCount;

}
