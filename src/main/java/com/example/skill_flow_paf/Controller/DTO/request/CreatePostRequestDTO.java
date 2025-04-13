package com.example.skill_flow_paf.Controller.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreatePostRequestDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    private List<MultipartFile> imageFiles;

}
