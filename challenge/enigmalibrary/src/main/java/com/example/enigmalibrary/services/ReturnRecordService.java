package com.example.enigmalibrary.services;

import com.example.enigmalibrary.dto.ReturnRecordSearchDTO;
import com.example.enigmalibrary.entities.ReturnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReturnRecordService {
    public ReturnRecord saveReturnRecord(ReturnRecord returnRecord);
    public ReturnRecord getReturnRecordById(String id);
    public Page<ReturnRecord> getReturnRecordPerPage(Pageable pageable, ReturnRecordSearchDTO returnRecordSearchDTO);

}
