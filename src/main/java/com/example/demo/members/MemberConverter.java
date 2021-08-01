package com.example.demo.members;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberConverter {

    public static Member entityToDomain(MemberEntity memberEntity) {
        return Member.of(memberEntity.getFirstname(), memberEntity.getLastname(), memberEntity.getIdcard(),
                Gender.of(memberEntity.getGender().getLabel()), memberEntity.getDateofbirth());
    }

    public static MemberEntity domainToEntity(Member memberDomain, GenderEntity genderEntity) {
        return new MemberEntity(null, memberDomain.getFirstname(),
                memberDomain.getLastname(), memberDomain.getIdCard(),
                genderEntity, memberDomain.getDateOfBirth());
    }
}
