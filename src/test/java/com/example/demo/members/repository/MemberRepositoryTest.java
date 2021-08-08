package com.example.demo.members.repository;

import com.example.demo.DatabaseIntegrationTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.example.demo.members.MemberEntity;
import com.example.demo.members.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest extends DatabaseIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    void setup() {
        GenderEntity male = new GenderEntity(null, "male");
        GenderEntity female = new GenderEntity(null, "female");

        genderRepository.saveAll(List.of(male,female));
    }


    @Test
    public void findMemberEntityByIdcard_returnsMember() {
        GenderEntity male = genderRepository.findByLabel("male");
        MemberEntity memberEntity = new MemberEntity(null, "Leo",
                "Vargas",123456, male, LocalDate.now());
        memberRepository.save(memberEntity);

        assertThat(memberRepository.findMemberEntityByIdcard(123456))
                .hasFieldOrPropertyWithValue("id",1L)
                .hasFieldOrPropertyWithValue("firstname","Leo")
                .hasFieldOrPropertyWithValue("lastname","Vargas");
    }

    @Test
    public void findMemberEntityByIdcard_returnsNull() {
        assertThat(memberRepository.findMemberEntityByIdcard(123456)).
                isEqualTo(null);
    }

}
