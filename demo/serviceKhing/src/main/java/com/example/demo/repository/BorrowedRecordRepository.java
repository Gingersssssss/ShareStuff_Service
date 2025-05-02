package com.example.demo.repository;

import com.example.demo.model.BorrowedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedRecordRepository extends JpaRepository<BorrowedRecord, Long> {
    List<BorrowedRecord> findByDueDateBefore(LocalDate date);
}