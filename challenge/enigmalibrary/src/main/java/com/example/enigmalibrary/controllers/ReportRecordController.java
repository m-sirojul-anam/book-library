package com.example.enigmalibrary.controllers;

import com.example.enigmalibrary.constant.ApiURLConstant;
import com.example.enigmalibrary.dto.ReportRecordSearchDTO;
import com.example.enigmalibrary.entities.ReportRecord;
import com.example.enigmalibrary.services.ReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.RECORD)
public class ReportRecordController {

    @Autowired
    ReportRecordService reportRecordService;

    // Method for Insert Data Report
    @PostMapping("/report")
    public ReportRecord saveReportRecord(@RequestBody ReportRecord reportRecord) {
        return reportRecordService.saveReportRecord(reportRecord);
    }

    // Method for Get Data Report By Id
    @GetMapping("/report/{id}")
    public ReportRecord getReportRecordById(@PathVariable String id) {
       return reportRecordService.getReportRecordById(id);
    }

    // Method for Get All Data Report Per Page
    @GetMapping("/report")
    public Page<ReportRecord> getReportRecordPerPage(
            @RequestBody ReportRecordSearchDTO reportRecordSearchDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ) {

        Pageable pageable = PageRequest.of(page, sizePerPage, sort);

        return reportRecordService.getReportRecordPerPage(pageable, reportRecordSearchDTO);
    }
}
