package com.example.dynamicfilter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public DataLoader(CountryRepository countryRepository, CompanyRepository companyRepository, EmployeeRepository employeeRepository, UserRepository userRepository, UserService userService) {
        this.countryRepository = countryRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) {
        UserFilter userFilter = new UserFilter();
        userFilter.setCompanyName("adpq");

        List<UserEntity> users = userService.getUsers(userFilter);
        System.out.println(users);

    }
}