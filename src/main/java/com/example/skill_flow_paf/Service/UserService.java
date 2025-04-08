package com.example.skill_flow_paf.Service;

import com.example.skill_flow_paf.Controller.DTO.request.CreateUserRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserResponseDTO;
import com.example.skill_flow_paf.Models.User;

import java.util.List;

public interface UserService {

    User createUser(CreateUserRequestDTO createUserRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    void deleteUserById(Long id);

    User updateUser(Long id, CreateUserRequestDTO createUserRequestDTO);
}
