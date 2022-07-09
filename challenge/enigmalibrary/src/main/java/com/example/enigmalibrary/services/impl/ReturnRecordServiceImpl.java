package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.ReturnRecordSearchDTO;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.ReturnRecord;
import com.example.enigmalibrary.repositories.BorrowingRecordRepository;
import com.example.enigmalibrary.repositories.ReturnRecordRepository;
import com.example.enigmalibrary.services.ReturnRecordService;
import com.example.enigmalibrary.specification.ReturnRecordSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReturnRecordServiceImpl implements ReturnRecordService {

    @Autowired
    ReturnRecordRepository returnRecordRepository;
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Override
    public ReturnRecord saveReturnRecord(ReturnRecord returnRecord) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(returnRecord.getBorrowingRecord().getId()).get();
        Integer getStockBook = borrowingRecord.getBook().getStock();
        LocalDate returnFromBorrowing = borrowingRecord.getBookReturnDate();
        LocalDate returnFromNow = returnRecord.getBookReturnDate();
            if (getStockBook <= 0) {
                return null;
            } else {
                borrowingRecord.getBook().setStock(getStockBook + 1);
                if(returnFromNow.isAfter(returnFromBorrowing)){
                    returnRecord.setStatusReturnDate("Late Return");
                } else if(returnFromNow.isEqual(returnFromBorrowing)){
                    returnRecord.setStatusReturnDate("On Time");
                }
                returnRecord.setBorrowingRecord(borrowingRecord);
                return returnRecordRepository.save(returnRecord);
            }
    }

    @Override
    public ReturnRecord getReturnRecordById(String id) {
        return returnRecordRepository.findById(id).get();
    }

    @Override
    public Page<ReturnRecord> getReturnRecordPerPage(Pageable pageable, ReturnRecordSearchDTO returnRecordSearchDTO)
    {
        Specification<ReturnRecord> returnRecordSpecification = ReturnRecordSpesification.getSpecification(returnRecordSearchDTO);
        return returnRecordRepository.findAll(returnRecordSpecification, pageable);
    }
}

