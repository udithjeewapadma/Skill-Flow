package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentReplyResponseDTO;
import com.example.skill_flow_paf.Exception.CommentNotFoundException;
import com.example.skill_flow_paf.Exception.CommentReplyException;
import com.example.skill_flow_paf.Models.Comment;
import com.example.skill_flow_paf.Models.CommentReply;
import com.example.skill_flow_paf.Repository.CommentReplyRepository;
import com.example.skill_flow_paf.Repository.CommentRepository;
import com.example.skill_flow_paf.Service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentReplyServiceImpl implements CommentReplyService {
    @Autowired
    private CommentReplyRepository commentReplyRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentReply createCommentReply(Long commentId, CreateCommentReplyRequestDTO createCommentReplyRequestDTO) throws CommentNotFoundException{
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CommentNotFoundException("comment id not found"));
        CommentReply commentReply = new CommentReply();
        commentReply.setReplyBody(createCommentReplyRequestDTO.getReplyBody());
        commentReply.setComment(comment);

        return commentReplyRepository.save(commentReply);
    }

    @Override
    public CommentReplyResponseDTO findById(Long id) {
        CommentReply commentReply = commentReplyRepository.findById(id).orElseThrow(()->new CommentReplyException("comment reply id is not found"));
        CommentReplyResponseDTO commentReplyResponseDTO = new CommentReplyResponseDTO();

        commentReplyResponseDTO.setCommentId(commentReply.getComment().getId());
        commentReplyResponseDTO.setId(commentReply.getId());
        commentReplyResponseDTO.setReplyBody(commentReply.getReplyBody());

        return commentReplyResponseDTO;
    }


}
