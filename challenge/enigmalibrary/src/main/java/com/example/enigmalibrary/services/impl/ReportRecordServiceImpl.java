package com.example.enigmalibrary.services.impl;

import com.example.enigmalibrary.dto.ReportRecordSearchDTO;
import com.example.enigmalibrary.entities.BorrowingRecord;
import com.example.enigmalibrary.entities.ReportRecord;
import com.example.enigmalibrary.entities.ReturnRecord;
import com.example.enigmalibrary.repositories.BorrowingRecordRepository;
import com.example.enigmalibrary.repositories.ReportRecordRepository;
import com.example.enigmalibrary.repositories.ReturnRecordRepository;
import com.example.enigmalibrary.services.ReportRecordService;
import com.example.enigmalibrary.specification.ReportRecordSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReportRecordServiceImpl implements ReportRecordService {

    @Autowired
    ReportRecordRepository reportRecordRepository;

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    ReturnRecordRepository returnRecordRepository;

    @Override
    public ReportRecord saveReportRecord(ReportRecord reportRecord) {

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(reportRecord.getBorrowingRecord().getId()).get();

        ReturnRecord returnRecord = returnRecordRepository.findById(reportRecord.getReturnRecord().getId()).get();

        reportRecord.setBorrowingRecord(borrowingRecord);
        reportRecord.setReturnRecord(returnRecord);

        return reportRecordRepository.save(reportRecord);
    }

    @Override
    public ReportRecord getReportRecordById(String id) {
        return reportRecordRepository.findById(id).get();
    }

    @Override
    public Page<ReportRecord> getReportRecordPerPage(Pageable pageable, ReportRecordSearchDTO reportRecordSearchDTO) {
        Specification<ReportRecord> reportRecordSpecification = ReportRecordSpesification.getSpecification(reportRecordSearchDTO);
        return reportRecordRepository.findAll(reportRecordSpecification, pageable);
    }
}
