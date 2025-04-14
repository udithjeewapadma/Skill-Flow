package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreatePostRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.PostResponseDTO;
import com.example.skill_flow_paf.Models.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostResponseDTO createPost(Long userId, Long categoryId, CreatePostRequestDTO createPostRequestDTO) throws IOException;

    List<PostResponseDTO> getAllPosts();

    PostResponseDTO findById(Long postId);

    PostResponseDTO updatePost(Long postId, CreatePostRequestDTO postDTO) throws IOException;


}
