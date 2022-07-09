package com.example.enigmalibrary.services;

import com.example.enigmalibrary.dto.BorrowingRecordSearchDTO;
import com.example.enigmalibrary.entities.BorrowingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BorrowingRecordService {
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord);
    public BorrowingRecord getBorrowingRecordById(String id);
    public Page<BorrowingRecord> getBorrowingRecordPerPage(Pageable pageable, BorrowingRecordSearchDTO borrowingRecordSearchDTO);
}
