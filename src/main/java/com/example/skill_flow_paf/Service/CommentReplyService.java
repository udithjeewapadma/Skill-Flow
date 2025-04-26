package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentReplyResponseDTO;
import com.example.skill_flow_paf.Models.CommentReply;

public interface CommentReplyService {
    CommentReply createCommentReply(Long commentId , CreateCommentReplyRequestDTO createCommentReplyRequestDTO);

    CommentReplyResponseDTO findById(Long id);

}
