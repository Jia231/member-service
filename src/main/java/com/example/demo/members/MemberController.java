package com.example.demo.members;

import com.example.demo.gender.Gender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addMember(@RequestBody MemberRequest newMember) {

        Member member = Member.of(newMember.getFirstname() , newMember.getLastname(),
                newMember.getIdCard(), Gender.of(newMember.getGender()), newMember.getDateOfBirth());

        Member storedMember = memberService.saveNewMember(member);
        return new ResponseEntity<>(
                String.format("Member with id %s has been saved", storedMember.getIdCard()),
                HttpStatus.OK);
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

}
