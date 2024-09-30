package com.example.dynamicfilter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private Long age;
    private List<EmployeeEntity> employeeEntities;
    private Boolean married;
    private String persianName;
    private String englishName;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private EmployeeEntity employee;

    public UserDTO(Long id, String persianName, String englishName, String email, String phone, String password, Gender gender) {
        this.id = id;
        this.persianName = persianName;
        this.englishName = englishName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }
}
