package com.example.demo.members.converter;

import com.example.demo.gender.Gender;
import com.example.demo.gender.GenderEntity;
import com.example.demo.members.Member;
import com.example.demo.members.MemberEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberConverter {

    public static Member entityToDomain(MemberEntity memberEntity) {
        return Member.of(memberEntity.getFirstname(), memberEntity.getLastname(), memberEntity.getIdcard(),
                Gender.of(memberEntity.getGender().getLabel().toLowerCase()), memberEntity.getDateofbirth());
    }

    public static MemberEntity domainToEntity(Member memberDomain, GenderEntity genderEntity) {
        return new MemberEntity(null, memberDomain.getFirstname(),
                memberDomain.getLastname(), memberDomain.getIdCard(),
                genderEntity, memberDomain.getDateOfBirth());
    }
}
