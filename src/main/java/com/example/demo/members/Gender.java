package com.example.demo.members;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
public enum Gender {
    MALE(1, "male"),
    FEMALE(2, "female");

    private final Integer value;
    private final String label;

    Gender(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(Integer genderId) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getValue().equals(genderId))
                .findFirst()
                .get()
                .getLabel();
    }

    public static Gender of(String genderLabel) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getLabel().equals(genderLabel))
                .findFirst()
                .orElse(null);
    }
}