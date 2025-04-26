package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentReplyRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentReplyResponseDTO;
import com.example.skill_flow_paf.Models.CommentReply;
import com.example.skill_flow_paf.Service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentReplies")
public class CommentReplyController {
    @Autowired
    private CommentReplyService commentReplyService;

    @PostMapping
    private CommentReplyResponseDTO createCommentReply(@RequestParam Long commentId, @RequestBody CreateCommentReplyRequestDTO createCommentReplyRequestDTO){
        CommentReply commentReply = commentReplyService.createCommentReply(commentId, createCommentReplyRequestDTO);

        CommentReplyResponseDTO commentReplyResponseDTO = new CommentReplyResponseDTO();
        commentReplyResponseDTO.setId(commentReply.getId());
        commentReplyResponseDTO.setReplyBody(commentReply.getReplyBody());
        commentReplyResponseDTO.setCommentId(commentReply.getComment().getId());

        return commentReplyResponseDTO;
    }

    @GetMapping("/{comment-reply-id}")
    private CommentReplyResponseDTO findById(@PathVariable("comment-reply-id") Long commentReplyId){
        return commentReplyService.findById(commentReplyId);
    }

    @GetMapping
    private List<CommentReplyResponseDTO> getCommentReplies(){
        return commentReplyService.findAll();
    }

    @DeleteMapping("/{comment-reply-id}")
    private void deleteById(@PathVariable("comment-reply-id") Long commentReplyId){
        commentReplyService.deleteById(commentReplyId);
    }
}
