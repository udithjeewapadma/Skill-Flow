package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCommentRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CommentResponseDTO;
import com.example.skill_flow_paf.Exception.CommentNotFoundException;
import com.example.skill_flow_paf.Exception.PostNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.Comment;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.CommentRepository;
import com.example.skill_flow_paf.Repository.PostRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long userId, Long postId, CreateCommentRequestDTO createCommentRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundExecption("User Not Found"));
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post Not Found"));
        
        Comment comment = new Comment();
        
        comment.setContent(createCommentRequestDTO.getContent());
        comment.setUser(user);
        comment.setPost(post);
        return commentRepository.save(comment);
        
    }

    @Override
    public CommentResponseDTO findById(Long id) throws CommentNotFoundException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("comment id not found"));

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setUserId(comment.getUser().getId());
        commentResponseDTO.setPostId(comment.getPost().getId());
        commentResponseDTO.setContent(comment.getContent());

        return commentResponseDTO;
    }

    @Override
    public List<CommentResponseDTO> findAll() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(comment -> {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setId(comment.getId());
            commentResponseDTO.setUserId(comment.getUser().getId());
            commentResponseDTO.setPostId(comment.getPost().getId());
            commentResponseDTO.setContent(comment.getContent());

            return commentResponseDTO;

        }).collect(Collectors.toList());

    }
}
