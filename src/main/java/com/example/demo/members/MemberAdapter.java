package com.example.demo.members;

import com.example.demo.gender.GenderAdapter;
import com.example.demo.gender.GenderEntity;
import com.example.demo.gender.GenderRepository;
import com.example.demo.members.converter.MemberConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberAdapter {

    private final MemberRepository memberRepository;
    private final GenderAdapter genderAdapter;

    public MemberEntity saveNewMember(Member newMember) {

        MemberEntity memberEntity = MemberConverter.domainToEntity(newMember,
                genderAdapter.findByLabel(newMember.getGender().getLabel()));

        return memberRepository.save(memberEntity);
    }
}
