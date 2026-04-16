package com.example.auth.controller;


import com.example.auth.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password)
    {
        if("user".equals(username) && "password".equals(password))
        {
            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Invalid Credentials");
    }
    @PostMapping("/validate")
    public Boolean validate(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                return true;
            }
        }
        throw new RuntimeException("Invalid Credentials");
    }

}
