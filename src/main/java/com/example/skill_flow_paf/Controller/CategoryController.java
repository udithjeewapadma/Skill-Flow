package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCategoryRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CategoryResponseDTO;
import com.example.skill_flow_paf.Models.Category;
import com.example.skill_flow_paf.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private CategoryResponseDTO createCategory(@RequestBody @Valid CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = categoryService.createCategory(createCategoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setCategoryName(category.getCategoryName());
        return categoryResponseDTO;
    }
}
