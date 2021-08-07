package com.example.demo.members;

import com.example.demo.members.converter.MemberConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberAdapter memberAdapter;

    public Member saveNewMember(Member member) {
        MemberEntity memberEntity = memberAdapter.saveNewMember(member);
        return MemberConverter.entityToDomain(memberEntity);
    }

    public List<Member> getMembers() {
        List<MemberEntity> memberEntities = memberAdapter.getMembers();
        List<Member> members = memberEntities.stream()
                .map( member -> MemberConverter.entityToDomain(member))
                .collect(Collectors.toList());
        return members;
    }
}
