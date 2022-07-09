package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.BorrowingRecordSearchDTO;
import com.example.enigmalibrary.entities.Book;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.Member;
import com.example.enigmalibrary.repositories.BookRepository;
import com.example.enigmalibrary.repositories.BorrowingRecordRepository;
import com.example.enigmalibrary.repositories.MemberRepository;
import com.example.enigmalibrary.services.BorrowingRecordService;
import com.example.enigmalibrary.specification.BorrowingRecordSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord) {

        Member member = memberRepository.findById(borrowingRecord.getMember().getId()).get();

        Book book = bookRepository.findById(borrowingRecord.getBook().getId()).get();

        if(book.getStock() <= 0){
            return null;
        } else {
            book.setStock(book.getStock() - 1);

            borrowingRecord.setMember(member);
            borrowingRecord.setBook(book);
            return borrowingRecordRepository.save(borrowingRecord);
        }
    }

    @Override
    public BorrowingRecord getBorrowingRecordById(String id) {
        return borrowingRecordRepository.findById(id).get();
    }

    @Override
    public Page<BorrowingRecord> getBorrowingRecordPerPage(Pageable pageable, BorrowingRecordSearchDTO borrowingRecordSearchDTO) {
        Specification<BorrowingRecord> borrowingRecordSpecification = BorrowingRecordSpecification.getSpecification(borrowingRecordSearchDTO);
        return borrowingRecordRepository.findAll(borrowingRecordSpecification, pageable);
    }
}
