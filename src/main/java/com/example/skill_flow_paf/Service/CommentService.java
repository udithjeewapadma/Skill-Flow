package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentRequestDTO;
import com.example.skill_flow_paf.Models.Comment;

public interface CommentService {
    Comment createComment(Long userId, Long postId, CreateCommentRequestDTO createCommentRequestDTO);
}
