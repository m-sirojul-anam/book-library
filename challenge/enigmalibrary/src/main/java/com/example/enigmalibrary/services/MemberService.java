package com.example.enigmalibrary.services;

import com.example.enigmalibrary.dto.MemberSearchDTO;
import com.example.enigmalibrary.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    public Member saveMember(Member member);
    public Member getMemberById(String id);
    public Member deleteMember(String id);
    public Page<Member> getBookPerPage(Pageable pageable, MemberSearchDTO memberSearchDTO);
}
