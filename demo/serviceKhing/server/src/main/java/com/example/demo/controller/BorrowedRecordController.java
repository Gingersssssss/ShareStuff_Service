package com.example.demo.controller;

import com.example.demo.model.BorrowedRecord;
import com.example.demo.repository.BorrowedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/borrowed-records")
public class BorrowedRecordController {

    @Autowired
    private BorrowedRecordRepository borrowedRecordRepository;

    // Get all borrowed records
    @GetMapping
    public List<BorrowedRecord> getAllBorrowedRecords() {
        return borrowedRecordRepository.findAll();
    }

    // Get borrowed records with remaining days before due date
    @GetMapping("/remaining")
    public List<BorrowedRecord> getBorrowedRecordsWithRemainingDays() {
        return borrowedRecordRepository.findAll();
    }

    // Send reminders for overdue items
    @PostMapping("/reminders")
    public ResponseEntity<?> sendReminders() {
        List<BorrowedRecord> overdueRecords = borrowedRecordRepository.findByDueDateBefore(LocalDate.now());
        // Logic to send reminders (e.g., email or notification)
        return ResponseEntity.ok("Reminders sent for " + overdueRecords.size() + " overdue items.");
    }
}