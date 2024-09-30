package com.example.dynamicfilter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeneralUserFilter {
    private List<GeneralUserFilterUtil> generalFilterUtils;

    @Getter
    @Setter
    public static class GeneralUserFilterUtil {
        private UserFilter filter;
        private Boolean or;
    }
}
