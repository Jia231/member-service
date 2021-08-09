package com.example.demo.members;

import com.example.demo.BaseComponentTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerComponentTest extends BaseComponentTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        genderRepository.deleteAll();
    }

    public void addMembers(){
        GenderEntity male = new GenderEntity(1L, "male");
        GenderEntity female = new GenderEntity(2L, "female");
        genderRepository.saveAll(List.of(male,female));
        MemberEntity member1 = new MemberEntity(null, "Luis", "Rojas", 12345, male, LocalDate.now());
        MemberEntity member2 = new MemberEntity(null, "Luisa", "Diaz", 22345, female, LocalDate.now());
        memberRepository.saveAll(List.of(member1, member2));

    }

    @Test
    @WithMockUser
    public void getMembers_returnsAllMembers() throws Exception {
        addMembers();
        ResultActions resultActions = mockMvc
                .perform(get(String.format("/member")))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<Member> members = asList(objectMapper.readValue(contentAsString, Member[].class));

        assertThat(members.get(0).getFirstname()).isEqualTo("Luis");
        assertThat(members.get(0).getGender().getLabel()).isEqualTo("male");

        assertThat(members.get(1).getFirstname()).isEqualTo("Luisa");
        assertThat(members.get(1).getGender().getLabel()).isEqualTo("female");
    }
}
