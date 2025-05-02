package com.example.demo.controller;

import com.example.demo.model.BorrowedRecord;
import com.example.demo.repository.BorrowedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BorrowedRecordController {

    @Autowired
    private BorrowedRecordRepository borrowedRepo;

    // ✅ GET /api/borrowed -> ส่งรายการยืมทั้งหมด
    @GetMapping("/borrowed")
    public List<BorrowedRecord> getAllBorrowed() {
        return borrowedRepo.findAll();
    }

    // ✅ POST /api/return/{id} -> คืนของ
    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnItem(@PathVariable Long id) {
        borrowedRepo.deleteById(id);
        return ResponseEntity.ok("Item returned successfully");
    }

}
