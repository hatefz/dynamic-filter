package com.example.dynamicfilter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "country")
    private List<CompanyEntity> companies;

    public CountryEntity(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}
