package com.example.demo.gender;

import com.example.demo.exceptions.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;

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
        Gender foundGender = Arrays.stream(Gender.values())
                .filter(gender -> gender.getLabel().equals(genderLabel))
                .findFirst()
                .orElse(null);

        if(isNull(foundGender)) {
            throw new NotFoundException("Gender not found");
        }

        return foundGender;
    }
}