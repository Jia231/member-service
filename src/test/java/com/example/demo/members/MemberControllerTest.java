package com.example.demo.members;

import com.example.demo.BaseUnitTest;
import com.example.demo.gender.Gender;
import com.example.demo.gender.GenderEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class MemberControllerTest extends BaseUnitTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private final String firstname = "Luis";
    private final String lastname = "Aguilar";
    private final Integer idCard = 123456;
    private final LocalDate birthDate = LocalDate.now();
    private final Gender gender = Gender.of("male");
    private final GenderEntity genderEntity = new GenderEntity(1L, "male");

    @Test
    public void getMembers_returnsAllMembers() {
        Member member = Member.of(firstname, lastname, idCard, gender, birthDate);
        when(memberService.getMembers()).thenReturn(List.of(member));
        List<Member> members = memberController.getMembers();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getFirstname()).isEqualTo(firstname);
    }

}
