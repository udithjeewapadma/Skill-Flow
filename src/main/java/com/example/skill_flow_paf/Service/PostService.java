package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreatePostRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.PostResponseDTO;
import com.example.skill_flow_paf.Models.Post;

import java.io.IOException;

public interface PostService {

    PostResponseDTO createPost(Long userId, Long categoryId, CreatePostRequestDTO createPostRequestDTO) throws IOException;
}
