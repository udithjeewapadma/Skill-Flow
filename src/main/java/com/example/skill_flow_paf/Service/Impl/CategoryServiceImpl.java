package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCategoryRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CategoryResponseDTO;
import com.example.skill_flow_paf.Exception.CategoryNotFoundException;
import com.example.skill_flow_paf.Models.Category;
import com.example.skill_flow_paf.Repository.CategoryRepository;
import com.example.skill_flow_paf.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = new Category();
        category.setCategoryName(createCategoryRequestDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public List<CategoryResponseDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(category.getId());
            categoryResponseDTO.setCategoryName(category.getCategoryName());
            return categoryResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryNotFoundException {
        categoryRepository.deleteById(id);
    }


}
