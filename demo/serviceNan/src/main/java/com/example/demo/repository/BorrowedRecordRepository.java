package com.example.demo.repository;

import com.example.demo.model.Borrowedecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedRecordRepository extends JpaRepository<Storage, Long> {
    List<BorrowRecord> findByDueDateBeforeAndReturnedFalse(LocalDate date);
}
