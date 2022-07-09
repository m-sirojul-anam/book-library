package com.example.enigmalibrary.controllers;

import com.example.enigmalibrary.constant.ApiURLConstant;
import com.example.enigmalibrary.dto.MemberSearchDTO;
import com.example.enigmalibrary.entities.Member;
import com.example.enigmalibrary.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.MEMBER)
public class MemberController {

    @Autowired
    MemberService memberService;

    // Method for Insert new Data Member
    @PostMapping
    public Member saveMember(@RequestBody Member member){return memberService.saveMember(member);}

    // Method for Get Member By Id
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id){return memberService.getMemberById(id);}

    // Method for Update Data Member
    @PutMapping
    public void updateMember(@RequestBody Member member){memberService.saveMember(member);}

    // Method For Soft Delete Data Member
    @PutMapping("/{id}")
    public Member deleteMember(@PathVariable String id){return memberService.deleteMember(id);}

    // Method for show all Data Member Per Page
    @GetMapping
    public Page<Member> getMemberPerPage(@RequestBody MemberSearchDTO memberSearchDTO,
                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
                                         Sort sort){
        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        return memberService.getBookPerPage(pageable, memberSearchDTO);
    }
}
