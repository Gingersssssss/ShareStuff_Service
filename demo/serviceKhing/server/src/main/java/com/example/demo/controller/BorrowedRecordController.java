package com.example.demo.controller;

import com.example.demo.model.ItemAll;
import com.example.demo.model.BorrowedRecord;
import com.example.demo.repository.BorrowedRecordRepository;
import com.example.demo.repository.ItemAllRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BorrowedRecordController {

    @Autowired
    private ItemAllRepository itemRepo;

    @Autowired
    private BorrowedRecordRepository borrowedRepo;

    // รวมข้อมูล: GET /api/dashboard?borrowId=1
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard(@RequestParam(required = false) Long borrowId) {
        Map<String, Object> response = new HashMap<>();

        // ส่วนที่ 1: รายการของทั้งหมด
        List<ItemAll> items = itemRepo.findAll();
        response.put("items", items);

        // ส่วนที่ 2: รายการยืมทั้งหมด พร้อมสถานะวันครบกำหนด
        List<BorrowedRecord> borrowedRecords = borrowedRepo.findAll();
        response.put("borrowedRecords", borrowedRecords);

        // ส่วนที่ 3: ยืมของ (ลดจำนวน + สร้างบันทึก)
        if (borrowId != null) {
            ItemAll item = itemRepo.findById(borrowId).orElse(null);
            if (item != null) {
                if (item.getQuantity() > 0) {
                    item.setQuantity(item.getQuantity() - 1);
                    itemRepo.save(item);

                    // สร้างบันทึกการยืมใหม่ (mock user id = 999, กำหนดวันยืม 7 วัน)
                    BorrowedRecord record = new BorrowedRecord();
                    record.setItemId(item.getId());
                    record.setBorrowedBy(999L);
                    record.setBorrowDate(java.time.LocalDate.now());
                    record.setDueDate(java.time.LocalDate.now().plusDays(7));
                    borrowedRepo.save(record);

                    response.put("borrowStatus", "Borrowed successfully");
                } else {
                    response.put("borrowStatus", "Item is out of stock");
                }
            } else {
                response.put("borrowStatus", "Item not found");
            }
        }

        return ResponseEntity.ok(response);
    }
}
