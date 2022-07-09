package com.example.enigmalibrary.repositories;

import com.example.enigmalibrary.entities.ReportRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReportRecordRepository extends JpaRepository<ReportRecord, String>, JpaSpecificationExecutor {
}
