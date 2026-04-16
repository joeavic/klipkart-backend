package com.nixcraft.User.controller;

import com.nixcraft.User.dto.UserRequestDTO;
import com.nixcraft.User.dto.UserResponseDTO;
import com.nixcraft.User.entity.User;
import com.nixcraft.User.service.AuthService;
import com.nixcraft.User.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
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
    public ResponseEntity<UserResponseDTO> addEmployee
            (@RequestBody @Valid UserRequestDTO e, HttpServletRequest request ) {

        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer "))
        {
            String token = header.substring(7);
            if(authService.validateToken(token)){
                log.info("VALIDATION DONE");
                userService.addEmployee(e);
            }

        }

        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteEmployee(@RequestParam Long id){
        return ResponseEntity.ok(userService.deleteEmployee(id));
    }

}
