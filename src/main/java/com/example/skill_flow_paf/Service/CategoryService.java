package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCategoryRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.CategoryResponseDTO;
import com.example.skill_flow_paf.Exception.CategoryNotFoundException;
import com.example.skill_flow_paf.Models.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

    Category findCategoryById(Long id) throws CategoryNotFoundException;

     List<CategoryResponseDTO> findAllCategories();

     void deleteCategoryById(Long id) throws CategoryNotFoundException;

     Category updateCategoryById(Long id, CreateCategoryRequestDTO createCategoryRequestDTO) throws CategoryNotFoundException;
}
