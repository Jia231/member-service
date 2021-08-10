package com.example.demo.members;

import com.example.demo.ComponentTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;

//Todo at some point maybe try to write a test with rest assured

@Component
public class MemberControllerComponentTest extends ComponentTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        memberRepository.deleteAll();
        genderRepository.deleteAll();
    }

    @Test
    public void addMember_returnAddedMember() throws Exception {
        addGenders();
        MemberRequest memberRequest = new MemberRequest("Luis", "Aguilar", "male",
                12345, LocalDate.now());
        Response response = given()
                .when()
                .header("auth-token", "aValidToken")
                .header("Content-type", "application/json")
                .body(memberRequest)
                .post("/member/add")
                .andReturn();

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
}
