package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.MemberSearchDTO;
import com.example.enigmalibrary.entities.Member;
import com.example.enigmalibrary.repositories.MemberRepository;
import com.example.enigmalibrary.services.MemberService;
import com.example.enigmalibrary.specification.MemberSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;
    Member member;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id).get();
    }

    @Override
    public Member deleteMember(String id) {
        member = memberRepository.findById(id).get();
        member.setDeleted(TRUE);
        return memberRepository.save(member);
    }

    @Override
    public Page<Member> getBookPerPage(Pageable pageable, MemberSearchDTO memberSearchDTO) {
        Specification<Member> memberSpecification = MemberSpecification.getSpecification(memberSearchDTO);
        return memberRepository.findAll(memberSpecification, pageable);
    }
}
