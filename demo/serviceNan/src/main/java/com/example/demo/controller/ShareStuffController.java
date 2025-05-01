package com.example.demo.controller;

import com.example.demo.model.BorrowedRecord;
import com.example.demo.model.Item;
import com.example.demo.repository.BorrowedRecordRepository;
import com.example.demo.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ShareStuffController {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private BorrowedRecordRepository borrowRepo;

    // Service 1: ค้นหาสิ่งของที่สามารถยืมได้
    @GetMapping("/items")
    public List<Item> search(@RequestParam(required = false, defaultValue = "") String name,
                             @RequestParam(required = false, defaultValue = "") String category) {
        return itemRepo.findByNameContainingAndCategoryContaining(name, category);
    }

    // Service 2: ยืมของ
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowItem(@RequestParam Long itemId,
                                             @RequestParam String user) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getQuantity() <= 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Item is out of stock");
        }

        item.setQuantity(item.getQuantity() - 1);
        itemRepo.save(item);

        BorrowedRecord record = new BorrowedRecord();
        record.setItemId(itemId);
        record.setBorrowedBy(user);
        record.setBorrowedAt(LocalDateTime.now());
        borrowRepo.save(record);

        return ResponseEntity.ok("Item borrowed by " + user);
    }

    // สำหรับดู log การยืม (debug client)
    @GetMapping("/borrow/logs")
    public List<BorrowedRecord> getBorrowLogs() {
        return borrowRepo.findAll();
    }
}
