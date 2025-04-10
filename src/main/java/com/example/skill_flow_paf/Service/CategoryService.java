package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateCategoryRequestDTO;
import com.example.skill_flow_paf.Exception.CategoryNotFoundException;
import com.example.skill_flow_paf.Models.Category;

public interface CategoryService {

    Category createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

    Category findCategoryById(Long id) throws CategoryNotFoundException;
}
