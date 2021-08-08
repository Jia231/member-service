package com.example.demo.members.service;

import com.example.demo.BaseUnitTest;
import com.example.demo.gender.Gender;
import com.example.demo.gender.GenderEntity;
import com.example.demo.members.Member;
import com.example.demo.members.MemberAdapter;
import com.example.demo.members.MemberEntity;
import com.example.demo.members.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MemberServiceTest extends BaseUnitTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberAdapter memberAdapter;

    private final String firstname = "Luis";
    private final String lastname = "Aguilar";
    private final Integer idCard = 123456;
    private final LocalDate birthDate = LocalDate.now();
    private final Gender gender = Gender.of("male");
    private final GenderEntity genderEntity = new GenderEntity(1L, "male");


    @Test
    public void saveNewMember_returnsSavedMember() {
        MemberEntity memberEntity = new MemberEntity(1L, firstname, lastname, idCard, genderEntity, birthDate);
        Member member = new Member(firstname, lastname, idCard, gender, birthDate);
        when(memberAdapter.saveNewMember(any())).thenReturn(memberEntity);
        memberService.saveNewMember(member);

        verify(memberAdapter, times(1)).saveNewMember(any());
    }

}
