package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreatePostRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.PostResponseDTO;
import com.example.skill_flow_paf.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost( @RequestParam Long userId,
                                                      @RequestParam Long categoryId,
                                                      @ModelAttribute @Valid CreatePostRequestDTO requestDTO) throws IOException {
        return ResponseEntity.ok(postService.createPost(categoryId,userId,requestDTO));
    }
}
