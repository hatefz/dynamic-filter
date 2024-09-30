package com.example.dynamicfilter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserFilter {
    private Long id;
    private List<Long> ids;
    private String persianName;
    private Boolean married;
    private String englishName;
    private String email;
    private String phone;
    private Gender gender;
    private String companyName;
    private String countryName;
    private Boolean or;
}
