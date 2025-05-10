package com.example.skill_flow_paf.Service.Impl;

import com.example.skill_flow_paf.Exception.UserNotFoundExecption;
import com.example.skill_flow_paf.Controller.DTO.request.CreateUserRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserResponseDTO;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setUsername(createUserRequestDTO.getUsername());
        user.setAbout(createUserRequestDTO.getAbout());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPhoneNumber(createUserRequestDTO.getPhoneNumber());
        user.setQualifications(createUserRequestDTO.getQualifications());
        String role = createUserRequestDTO.getRole();
        user.setRole((role != null && !role.isEmpty()) ? role : "user");
        return userRepository.save(user);
    }

    @Override
    public UserResponseDTO getUserById(Long id) throws UserNotFoundExecption {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundExecption("User not found"));

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setAbout(user.getAbout());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setQualifications(user.getQualifications());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;

    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setAbout(user.getAbout());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setPhoneNumber(user.getPhoneNumber());
            userResponseDTO.setQualifications(user.getQualifications());
            userResponseDTO.setRole(user.getRole());

            return userResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, CreateUserRequestDTO createUserRequestDTO) {

        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundExecption("User Id" + id + " not found"));

        existingUser.setAbout(createUserRequestDTO.getAbout());
        existingUser.setPhoneNumber(createUserRequestDTO.getPhoneNumber());
        existingUser.setQualifications(createUserRequestDTO.getQualifications());

        return userRepository.save(existingUser);

    }

}
