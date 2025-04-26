package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.request.CreateReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentReplyResponseDTO;
import com.example.skill_flow_paf.Models.CommentReply;

import java.util.List;

public interface CommentReplyService {
    CommentReply createCommentReply(Long commentId , CreateCommentReplyRequestDTO createCommentReplyRequestDTO);

    CommentReplyResponseDTO findById(Long id);

    List<CommentReplyResponseDTO> findAll();

    void deleteById(Long id);


    CommentReply updateCommentReply(Long id, CreateCommentReplyRequestDTO createCommentReplyRequestDTO);



}
