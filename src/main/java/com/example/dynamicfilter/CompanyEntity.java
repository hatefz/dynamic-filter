package com.example.dynamicfilter;

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
public class CompanyEntity {
    @Id
    private Long id;
    private String name;
    @JoinColumn
    @ManyToOne
    private CountryEntity country;

}
