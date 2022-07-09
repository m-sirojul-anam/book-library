package com.example.enigmalibrary.services;

import com.example.enigmalibrary.dto.ReportRecordSearchDTO;
import com.example.enigmalibrary.entities.ReportRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportRecordService {
    public ReportRecord saveReportRecord(ReportRecord reportRecord);
    public ReportRecord getReportRecordById(String id);
    public Page<ReportRecord> getReportRecordPerPage(Pageable pageable, ReportRecordSearchDTO reportRecordSearchDTO);

}
