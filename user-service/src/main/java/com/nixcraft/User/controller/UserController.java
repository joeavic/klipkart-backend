package com.nixcraft.User.controller;

import com.nixcraft.User.dto.UserRequestDTO;
import com.nixcraft.User.dto.UserResponseDTO;
import com.nixcraft.User.entity.User;
import com.nixcraft.User.service.AuthService;
import com.nixcraft.User.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getEmployees(){
        return ResponseEntity.ok(userService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser
            (@RequestBody @Valid UserRequestDTO e) {
        UserResponseDTO user = userService.addEmployee(e);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteEmployee(@RequestParam Long id){
        return ResponseEntity.ok(userService.deleteEmployee(id));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable
           @NotBlank(message = "Email cannot be empty")
           @Email(message = "Invalid Email format")
           String email) {

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
