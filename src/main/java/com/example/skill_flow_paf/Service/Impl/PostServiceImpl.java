package com.example.skill_flow_paf.Service.Impl;

import com.cloudinary.Cloudinary;
import com.example.skill_flow_paf.Controller.DTO.request.CreatePostRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.PostResponseDTO;
import com.example.skill_flow_paf.Exception.CategoryNotFoundException;
import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Models.Category;
import com.example.skill_flow_paf.Models.Post;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.CategoryRepository;
import com.example.skill_flow_paf.Repository.PostRepository;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private  PostRepository postRepository;

    private final Cloudinary cloudinary;

    public PostServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           PostRepository postRepository,
                           Cloudinary cloudinary) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public PostResponseDTO createPost(Long categoryId, Long userId,CreatePostRequestDTO createPostRequestDTO) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExecption("User not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Post post = new Post();
        post.setTitle(createPostRequestDTO.getTitle());
        post.setDescription(createPostRequestDTO.getDescription());
        post.setUser(user);
        post.setCategory(category);
        List<String> imageUrls = new ArrayList<>();

        // Upload images to Cloudinary
        for (MultipartFile file : createPostRequestDTO.getImageFiles()) {
            String imageUrl = cloudinary.uploader()
                    .upload(file.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()))
                    .get("url")
                    .toString();
            imageUrls.add(imageUrl);
        }
        post.setImageUrl(imageUrls);

        Post savedPost = postRepository.save(post);
        return new PostResponseDTO(
                savedPost.getId(), savedPost.getTitle(), savedPost.getDescription(),
                savedPost.getImageUrl(), savedPost.getUser().getId(),
                savedPost.getCategory().getId());
    }
}
