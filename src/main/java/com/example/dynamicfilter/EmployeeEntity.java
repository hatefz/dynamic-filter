package com.example.dynamicfilter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn
    private UserEntity user;
    @ManyToOne
    @JoinColumn
    private CompanyEntity company;
}
