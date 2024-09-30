package com.example.dynamicfilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public UserController(
            UserService userService,
            EmployeeRepository employeeRepository,
            UserRepository userRepository) {
        this.userService = userService;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Boolean> activeUsers() {
        for (long i = 0; i < 100_000; i++) {
            UserEntity user = userRepository.getReferenceById(i+1);
            EmployeeEntity employee = employeeRepository.getReferenceById(i+1);
            user.setEmployee(employee);
            userRepository.save(user);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<UserEntity>> getUsers(UserFilter userFilter, Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(userFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<UserEntity>> getUsersList(UserFilter userFilter) {
        return new ResponseEntity<>(userService.getUsers(userFilter), HttpStatus.OK);
    }

    @GetMapping("/general")
    @ResponseBody
    public ResponseEntity<Page<UserEntity>> getUsers(@RequestBody GeneralUserFilter userFilter, Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(userFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/general/list")
    @ResponseBody
    public ResponseEntity<List<UserEntity>> getUsersList(@RequestBody GeneralUserFilter userFilter) {
        return new ResponseEntity<>(userService.getUsers(userFilter), HttpStatus.OK);
    }
}
