package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCategoryRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CategoryResponseDTO;
import com.example.skill_flow_paf.Exception.CategoryNotFoundException;
import com.example.skill_flow_paf.Models.Category;
import com.example.skill_flow_paf.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{category-id}")
    public CategoryResponseDTO getCategoryById(@PathVariable("category-id") Long id) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(id);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setCategoryName(category.getCategoryName());
        return categoryResponseDTO;
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
