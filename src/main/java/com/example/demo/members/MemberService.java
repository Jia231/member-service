package com.example.demo.members;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberAdapter memberAdapter;

    public Member saveNewMember(Member member) {
        MemberEntity memberEntity = memberAdapter.saveNewMember(member);
        return MemberConverter.entityToDomain(memberEntity);
    }
}
