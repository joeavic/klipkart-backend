package com.nixcraft.User.service;


import com.nixcraft.User.dto.UserRequestDTO;
import com.nixcraft.User.dto.UserResponseDTO;
import com.nixcraft.User.entity.User;
import com.nixcraft.User.exception.UserNotFoundException;
import com.nixcraft.User.kafka.event.UserEvent;
import com.nixcraft.User.kafka.producer.UserProducer;
import com.nixcraft.User.mapper.UserMapper;
import com.nixcraft.User.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProducer userProducer;

    @Autowired
    private AuthService authService;

    private String dbConnection;

    public List<UserResponseDTO> getEmployees() {

        log.info("Request to get all Employees Data received {}", Instant.now());

        List<User> employeeList = userRepository.findAll();

        List<String> nameList = employeeList.stream().map(User::getName).toList();

        log.info("Employee List {}", nameList);

        return employeeList.stream()
                .map(UserMapper::toUserResponseDTO).toList();
    }

    public UserResponseDTO addEmployee(UserRequestDTO e) {

        log.info("EmployeeReq received {}", e);



        User employee = UserMapper.toEntity(e);
        User saved = userRepository.save(employee);
        log.info("Employee Saved ");

        // kafka event generation
        UserEvent event = new UserEvent(
                "CREATED",
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone()
        );
        userProducer.publishEmployeeCreated(event);

        return UserMapper.toUserResponseDTO(saved);
    }

    public User deleteEmployee(Long id) {
        User e = null;
        if(userRepository.findById(id).isPresent()){
            e = userRepository.findById(id).get();
            userRepository.delete(e);
        }
        return e;
    }

    public UserResponseDTO getUserByEmail(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));

        return UserMapper.toUserResponseDTO(user);
    }
}
