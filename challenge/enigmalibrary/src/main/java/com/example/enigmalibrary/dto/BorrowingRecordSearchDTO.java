package com.example.enigmalibrary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BorrowingRecordSearchDTO {
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate searchStartBorrowingDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate searchEndBorrowingDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate searchStartReturnDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate searchEndReturnDate;
    private String searchMemberId;
    private String searchMemberFirstName;
    private String searchMemberLastName;
    private String searchBookId;
    private String searchBookTitle;
    private String searchBookAuthor;
}
