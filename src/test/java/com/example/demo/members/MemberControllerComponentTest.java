package com.example.demo.members;

import com.example.demo.BaseComponentTest;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

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
    void setUp() {
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



    /*public static String clientId = "${spring.security.oauth2.client.registration.google.client-id}";
    public static String redirectUri = "some_redirect_uri";
    public static String scope = "some_scope";
    public static String username = "some_email";
    public static String password = "some_password";

    public static String encode(String str1, String str2) {
        return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
    }

    public static Response getCode() {
        String authorization = encode(username, password);

        return
                given()
                        .header("authorization", "Basic " + authorization)
                        .contentType(ContentType.URLENC)
                        .formParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", scope)
                        .post("/oauth2/authorize")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static String parseForOAuth2Code(Response response) {
        return response.jsonPath().getString("code");
    }*/

    public static String asJsonString(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    @WithMockUser
    public void addMember_returnAddedMember() throws Exception {
        addGenders();
        MemberRequest memberRequest = new MemberRequest("Casius", "Klay",
                "male", 12345, LocalDate.now());
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/member/add")
                        .content(asJsonString(memberRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        List<MemberEntity> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getFirstname()).isEqualTo("Casius");
        assertThat(members.get(0).getIdcard()).isEqualTo(12345);

    }

    /*@Test
    @WithMockUser
    public void addMember_returnsMember() throws Exception {
        MemberRequest memberRequest = new MemberRequest("Casius", "Klay",
                "male", 12345, LocalDate.now());

        given().sessionAttr("name", value)

        Response response = given()
                .when()
                .header("auth-token", "aValidToken")
                .header("Content-type", "application/json")
                .body(memberRequest)
                .post("/member/add")
                 .andReturn();

        Response response = given()
                .when()
                .header("Content-type", "application/json")
                .body(memberRequest)
                .post("/member/add")
                .andReturn();
        given().get("/member").then().statusCode(200);
        Response response = given()
                .body(memberRequest)
                .redirects()
                .follow(false)
                .expect().statusCode(302)
                .when()
                .post("/member/add");

        String headerLocationValue = response.getHeader("Location");

        Response resp2 =
                given().
                        cookie(response.getDetailedCookie("JSESSIONID")).
                        expect().
                        statusCode(200).
                        when().
                        get(headerLocationValue);

        Response response3 = given()
                .when()
                .cookie(response.getDetailedCookie("JSESSIONID"))
                .body(memberRequest)
                .post("/member/add")
                .andReturn();
        given().
                filter((requestSpec, responseSpec, ctx) -> {
                    // Your code for generating token using OAuth
                    String header = requestSpec.header("AUTH", header );
                    return ctx.next(requestSpec, responseSpec);
                }).
                when().
                get("/customAuth").
                then().
                statusCode(200);

        System.out.println("Hello");
    }*/

}
