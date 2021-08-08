package com.example.demo.members.converter;

import com.example.demo.BaseUnitTest;
import com.example.demo.gender.Gender;
import com.example.demo.gender.GenderEntity;
import com.example.demo.members.Member;
import com.example.demo.members.MemberEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberConverterTest extends BaseUnitTest {

    private final String firstname = "Luis";
    private final String lastname = "Aguilar";
    private final Integer idCard = 123456;
    private final LocalDate birthDate = LocalDate.now();
    private final Gender gender = Gender.of("male");
    private final GenderEntity genderEntity = new GenderEntity(1L, "male");

    @Test
    public void convertEntityToDomain() {
        MemberEntity memberEntity = new MemberEntity(1L, firstname, lastname, idCard, genderEntity, birthDate);
        Member member1 = Member.of(firstname, lastname, idCard, gender, birthDate);

        Member member = MemberConverter.entityToDomain(memberEntity);

        assertThat(member).isEqualTo(member1);
    }

    @Test
    public void convertDomainToEntity() {
        MemberEntity memberEntity = new MemberEntity(null, firstname, lastname, idCard, genderEntity, birthDate);
        Member member1 = Member.of(firstname, lastname, idCard, gender, birthDate);

        MemberEntity memberEntity1 = MemberConverter.domainToEntity(member1, genderEntity);

        assertThat(memberEntity1).isEqualTo(memberEntity);
    }
}
