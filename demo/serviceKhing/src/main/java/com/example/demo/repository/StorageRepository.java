package com.example.demo.repository;

import com.example.demo.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    ResponseEntity<Void> deleteHandytool(@PathVariable Long id);
}