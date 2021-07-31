package com.example.demo.members;

import lombok.Getter;

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
}