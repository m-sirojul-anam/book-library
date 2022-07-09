package com.example.enigmalibrary.controllers;

import com.example.enigmalibrary.constant.ApiURLConstant;
import com.example.enigmalibrary.dto.BorrowingRecordSearchDTO;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.RECORD)
public class BorrowingRecordController {

    @Autowired
    BorrowingRecordService borrowingRecordService;

    // Method For Insert new data Borrowing
    @PostMapping("/borrowing")
    public BorrowingRecord saveBorrowingRecord(
            @RequestBody BorrowingRecord borrowingRecord
    ){
        return borrowingRecordService.saveBorrowingRecord(borrowingRecord);
    }

    // Method For Update Data Borrowing
    @PutMapping("/borrowing")
    public BorrowingRecord updateBorrowingRecord(@RequestBody BorrowingRecord borrowingRecord){return saveBorrowingRecord(borrowingRecord);}

    // Method For Get borrowing By id
    @GetMapping("/borrowing/{id}")
    public BorrowingRecord getBorrowingRecordById(@PathVariable String id){return borrowingRecordService.getBorrowingRecordById(id);}

    // Method For Get All Borrowing Per Page
    @GetMapping("/borrowing")
    public Page<BorrowingRecord> getBookPerPage(
            @RequestBody BorrowingRecordSearchDTO borrowingRecordSearchDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){

        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        return borrowingRecordService.getBorrowingRecordPerPage(pageable, borrowingRecordSearchDTO);
    }
}
