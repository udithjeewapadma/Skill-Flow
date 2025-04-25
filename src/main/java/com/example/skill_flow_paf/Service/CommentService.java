package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentResponseDTO;
import com.example.skill_flow_paf.Models.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long userId, Long postId, CreateCommentRequestDTO createCommentRequestDTO);

    CommentResponseDTO findById(Long id);

    List<CommentResponseDTO> findAll();
}
