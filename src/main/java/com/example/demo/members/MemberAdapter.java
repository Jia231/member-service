package com.example.demo.members;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberAdapter {

    private final MemberRepository memberRepository;
    private final GenderRepository genderRepository;

    public MemberEntity saveNewMember(Member newMember) {
        GenderEntity genderEntity = genderRepository.findByLabel(newMember.getGender().getLabel());
        MemberEntity memberEntity = MemberConverter.domainToEntity(newMember, genderEntity);

        return memberRepository.save(memberEntity);
    }
}
