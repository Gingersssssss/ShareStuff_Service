package com.example.demo.repository;

import com.example.demo.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    List<Storage> findByBorrowed(boolean borrowed);
}
