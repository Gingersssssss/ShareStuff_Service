package com.example.demo.controller;

import com.example.demo.model.Storage;
import com.example.demo.repository.StorageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final StorageRepository repository;

    public StorageController(StorageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Storage> getAll() {
        return repository.findAll();
    }

    @GetMapping("/available")
    public List<Storage> getAvailable() {
        return repository.findByBorrowed(false);
    }

    @PutMapping("/borrow/{id}")
    public ResponseEntity<?> borrowItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    if (item.isBorrowed()) {
                        return ResponseEntity.status(409).body("Item already borrowed");
                    }
                    item.setBorrowed(true);
                    repository.save(item);
                    return ResponseEntity.ok("Item borrowed successfully");
                })
                .orElse(ResponseEntity.status(404).body("Item with ID " + id + " not found"));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnItem(@PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    if (!item.isBorrowed()) {
                        return ResponseEntity.status(409).body("Item is not currently borrowed");
                    }
                    item.setBorrowed(false);
                    repository.save(item);
                    return ResponseEntity.ok("Item returned successfully");
                })
                .orElse(ResponseEntity.status(404).body("Item with ID " + id + " not found"));
    }
}
