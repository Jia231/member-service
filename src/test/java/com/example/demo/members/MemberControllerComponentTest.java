package com.example.demo.members;

import com.example.demo.BaseComponentTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Todo at some point maybe try to write a test with rest assured


public class MemberControllerComponentTest extends BaseComponentTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /*@LocalServerPort
    int port;*/

    @BeforeEach
    void setUpTests() {
        //RestAssured.port = port;
        memberRepository.deleteAll();
        genderRepository.deleteAll();
    }

    public void addGenders() {
        GenderEntity male = new GenderEntity(null, "male");
        GenderEntity female = new GenderEntity(null, "female");
        genderRepository.saveAll(List.of(male,female));
    }

    public void addMembers(){

        MemberEntity member1 = new MemberEntity(null, "Luis", "Rojas", 12345,
                getByLabel("male"), LocalDate.now());
        MemberEntity member2 = new MemberEntity(null, "Luisa", "Diaz", 22345,
                getByLabel("female"), LocalDate.now());
        memberRepository.saveAll(List.of(member1, member2));

    }

    public GenderEntity getByLabel(String label) {
        List<GenderEntity> genders = genderRepository.findAll();
        return genders.stream().filter(gender -> gender.getLabel().contains(label)).findFirst().orElse(null);
    }

    @Test
    @WithMockUser
    public void getMembers_returnsAllMembers() throws Exception {
        addGenders();
        addMembers();
        ResultActions resultActions = mockMvc
                .perform(get(String.format("/member")))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<Member> members = asList(objectMapper.readValue(contentAsString, Member[].class));

        assertThat(members.size()).isEqualTo(2);

        assertThat(members.get(0).getFirstname()).isEqualTo("Luis");
        assertThat(members.get(0).getGender().getLabel()).isEqualTo("male");

        assertThat(members.get(1).getFirstname()).isEqualTo("Luisa");
        assertThat(members.get(1).getGender().getLabel()).isEqualTo("female");
    }

    @Test
    public void getMembers() {
        String url = String.format("/member");
        Response controllerResponse = given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().response();
    }

}
