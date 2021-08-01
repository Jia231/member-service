package com.example.demo.gender;

import com.example.demo.exceptions.NotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static java.util.Objects.isNull;

@Getter
@Slf4j
public enum Gender {
    MALE(1, "male"),
    FEMALE(2, "female");

    private final Integer value;
    private final String label;

    Gender(Integer value, String label) {
        this.value = value;
        this.label = label;
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