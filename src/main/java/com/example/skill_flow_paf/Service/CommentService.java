package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentRequestDTO;

public interface CommentService {
    CommentService createComment(Long commentId, CreateCommentRequestDTO createCommentRequestDTO);
}
