package com.example.demo.members;

import com.example.demo.gender.Gender;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Member {
    private final String firstname;
    private final String lastname;
    private final Integer idCard;
    private final Gender gender;
    private final LocalDate dateOfBirth;

    public static Member of(String firstname, String lastname, Integer idCard, Gender gender, LocalDate dateOfBirth) {
        return new Member(firstname, lastname, idCard, gender, dateOfBirth);
    }

}
