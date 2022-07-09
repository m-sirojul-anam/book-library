package com.example.enigmalibrary.repositories;

import com.example.enigmalibrary.entities.ReturnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReturnRecordRepository extends JpaRepository<ReturnRecord, String>, JpaSpecificationExecutor {
}
