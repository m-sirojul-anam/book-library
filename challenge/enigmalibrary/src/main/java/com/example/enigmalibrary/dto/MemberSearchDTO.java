package com.example.enigmalibrary.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberSearchDTO {
    private String searchMemberFirstName;
    private String searchMemberLastName;
    private String searchMemberGender;
    private Date searchMemberStartDateOfBirth;
    private Date searchMemberEndDateOfBirth;
    private String searchMemberAddress;
    private String searchMemberPhone;
    private String searchMemberUsername;
}
