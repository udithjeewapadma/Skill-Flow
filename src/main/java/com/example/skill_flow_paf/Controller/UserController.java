package com.example.skill_flow_paf.Controller;

import com.example.skill_flow_paf.Controller.DTO.request.CreateUserRequestDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserResponseDTO;
import com.example.skill_flow_paf.Controller.DTO.response.UserWrapperDTO;
import com.example.skill_flow_paf.Models.User;
import com.example.skill_flow_paf.Repository.UserRepository;
import com.example.skill_flow_paf.Service.Impl.OAuth2TokenService;
import com.example.skill_flow_paf.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/users")
public class UserController {

    private final OAuth2TokenService oauth2TokenService;
    private final UserRepository userRepository;

    public UserController(OAuth2TokenService oauth2TokenService, UserRepository userRepository) {
        this.oauth2TokenService = oauth2TokenService;
        this.userRepository = userRepository;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/session-id")
    public String getSessionId(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/by-session")
    public ResponseEntity<?> getUserDetailsBySessionId(@RequestParam String sessionId, HttpServletRequest request) {

        System.out.println(sessionId);
        HttpSession session = request.getSession(false);

        // Validate session
        if (session == null || !session.getId().equals(sessionId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired session");
        }
        System.out.println(sessionId);
        // Get the SecurityContext from the session
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Security context not found in session");
        }

        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        System.out.println(email + " " + name);

        // Fetch user from the database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(name);
        responseDTO.setEmail(email);
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        responseDTO.setAbout(user.getAbout());
        responseDTO.setRole(user.getRole());
        responseDTO.setQualifications(user.getQualifications());
        responseDTO.setProfileImageUrl(user.getProfileImageUrl());
        return ResponseEntity.ok(responseDTO);

    }


    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
        User user = userService.createUser(createUserRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
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
