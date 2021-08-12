package com.example.demo.members;

import com.example.demo.ComponentTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class MemberControllerComponentTest extends ComponentTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GenderRepository genderRepository;


    @Before
    public void setup() {
        memberRepository.deleteAll();
        genderRepository.deleteAll();
    }

    @Test
    public void saveMilestones_return400_whenCreatingMilestoneWithInvalidDateFormat() {
        createGenders();
        MemberRequest memberRequest = new MemberRequest("Lucas","Viera",
                "male", 12345, LocalDate.now());
        Response response = given()
                .when()
                .header("auth-token", "aValidToken")
                .header("Content-type", "application/json")
                .body(memberRequest)
                .post(String.format("/member/add"))
                .andReturn();

        assertThat(response.getStatusCode()).isEqualTo(400);
        assertThat(response.print()).isNotBlank();
    }

    public void createGenders() {
        GenderEntity male = new GenderEntity(null, "male");
        GenderEntity female = new GenderEntity(null, "female");
        genderRepository.saveAll(List.of(male,female));
    }

}
