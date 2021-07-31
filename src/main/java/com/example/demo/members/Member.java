package com.example.demo.members;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Member {
    private final String firstname;
    private final String lastname;
    private final Integer gender;
    private final LocalDate dateOfBirth;

    public static Member of(String firstname, String lastname, String gender, LocalDate dateOfBirth) {
        return new Member(firstname, lastname, getGenderValue(gender), dateOfBirth);
    }

    private static Integer getGenderValue(String gender) {
        Gender genderEnum = Arrays.stream(Gender.values())
                .filter(g -> g.getLabel().equals(gender))
                .findFirst().get();
        return genderEnum.getValue();
    }

}
