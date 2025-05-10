package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateUserRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserResponseDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserWrapperDTO;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
        User user = userService.createUser(createUserRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAbout(user.getAbout());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setQualifications(user.getQualifications());
        return userResponseDTO;
    }

    @GetMapping("/{user-id}")
    public UserResponseDTO getUserById(@PathVariable("user-id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<UserWrapperDTO> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new UserWrapperDTO(users.size(), users));
    }

    @DeleteMapping("/{user-id}")
    public void deleteUserById(@PathVariable("user-id") Long id) {
        userService.deleteUserById(id);
    }


    @PutMapping("/{user-id}")
    public UserResponseDTO updateUser(@PathVariable("user-id")
                                      Long id, @RequestBody @Valid
                                      CreateUserRequestDTO createUserRequestDTO) {
        User user=  userService.updateUser(id, createUserRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAbout(user.getAbout());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setQualifications(user.getQualifications());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());

        return userResponseDTO;
    }
}
