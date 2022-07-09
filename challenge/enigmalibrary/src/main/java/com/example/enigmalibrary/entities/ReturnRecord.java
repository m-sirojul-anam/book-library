package com.example.enigmalibrary.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book_return_record")
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRecord {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate bookReturnDate = LocalDate.now();

    private String statusReturnDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowing_record_id", referencedColumnName = "id")
    private BorrowingRecord borrowingRecord;

//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    private Member member;
//
//    @JsonIgnoreProperties({"hibernateLazyInitializer"})
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
//    private Book book;
}
