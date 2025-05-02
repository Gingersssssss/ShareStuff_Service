package com.example.demo.controller;

import com.example.demo.model.BorrowedRecord;
import com.example.demo.repository.BorrowedRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BorrowedRecordController {

    @Autowired
    private BorrowedRecordRepository borrowedRepo;

    // รวมข้อมูล: GET /api/dashboard?borrowId=1
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard(@RequestParam(required = false) Long borrowId) {
        Map<String, Object> response = new HashMap<>();

        // ส่วนที่ 2: รายการยืมทั้งหมด พร้อมสถานะวันครบกำหนด
        List<BorrowedRecord> borrowedRecords = borrowedRepo.findAll();
        response.put("borrowedRecords", borrowedRecords);

        return ResponseEntity.ok(response);
    }

    // ส่งคำขอทวงคืนสินค้า: POST /api/reminders
    @PostMapping("/reminders")
    public ResponseEntity<Map<String, Object>> sendReminders() {
        List<BorrowedRecord> overdueRecords = borrowedRepo.findByDueDateBefore(java.time.LocalDate.now());
        Map<String, Object> response = new HashMap<>();
        response.put("overdueRecords", overdueRecords);
        response.put("message", "Reminders sent for " + overdueRecords.size() + " overdue items.");
        return ResponseEntity.ok(response);
    }
}
