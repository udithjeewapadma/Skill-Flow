package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentResponseDTO;
import com.example.skill_flow_paf.Models.Comment;
import com.example.skill_flow_paf.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    private CommentResponseDTO createComment(@RequestParam Long postId, @RequestParam Long userId, @RequestBody CreateCommentRequestDTO createCommentRequestDTO){

        Comment comment = commentService.createComment(postId,userId,createCommentRequestDTO);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setUserId(comment.getUser().getId());
        commentResponseDTO.setPostId(comment.getPost().getId());
        commentResponseDTO.setContent(comment.getContent());

        return commentResponseDTO;
    }

    @GetMapping("/{comment-id}")
    private CommentResponseDTO getCommentById(@PathVariable("comment-id") Long commentId){
        return commentService.findById(commentId);
    }

    @GetMapping
    private List<CommentResponseDTO> getAllComments(){
        return commentService.findAll();
    }

    @DeleteMapping("/{comment-id}")
    private void deleteComment(@PathVariable("comment-id") Long commentId){
        commentService.deleteComment(commentId);
    }

    @PutMapping("/{comment-id}")
    private CommentResponseDTO updateCommentById(@PathVariable("comment-id") Long commentId, @RequestBody CreateCommentRequestDTO createCommentRequestDTO){

        Comment comment = commentService.updateCommentById(commentId,createCommentRequestDTO);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

        commentResponseDTO.setContent(comment.getContent());

        return commentResponseDTO;
    }
}
