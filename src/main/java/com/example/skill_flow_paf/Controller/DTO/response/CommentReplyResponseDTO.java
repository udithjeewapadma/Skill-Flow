package com.example.skill_flow_paf.Controller.DTO.response;

import lombok.Data;

@Data
public class CommentReplyResponseDTO {
    private Long Id;
    private Long commentId;

    private String replyBody;


}

