package com.example.demo.members;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/add/member")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addMember(@RequestBody MemberRequest newMember) {

        Member member = Member.of(newMember.getFirstname() , newMember.getLastname(),
                newMember.getIdCard(), Gender.of(newMember.getGender()), newMember.getDateOfBirth());

        Member storedMember = memberService.saveNewMember(member);
        return new ResponseEntity<>(
                String.format("Member with id %s has been saved", storedMember.getIdCard()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity getMembers() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
