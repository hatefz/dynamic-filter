package com.example.dynamicfilter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private Long id;
    private Long age;
    private Boolean married;
    private String persianName;
    private String englishName;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnore
    private EmployeeEntity employee;

    public UserEntity(Long id, String persianName, String englishName, String email, String phone, String password, Gender gender) {
        this.id = id;
        this.persianName = persianName;
        this.englishName = englishName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
    }
}
