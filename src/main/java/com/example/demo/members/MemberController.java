package com.example.demo.members;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @PostMapping(value = "/add/member")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addMember(@RequestBody MemberRequest newMember) {

        System.out.println(newMember);
        return null;
    }

    @GetMapping(value = "/")
    public ResponseEntity getMembers() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
