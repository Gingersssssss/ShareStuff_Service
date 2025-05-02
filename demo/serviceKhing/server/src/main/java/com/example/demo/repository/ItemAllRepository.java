package com.example.demo.repository;

import com.example.demo.model.ItemAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAllRepository extends JpaRepository<ItemAll, Long> {
    List<ItemAll> findByNameContainingIgnoreCase(String name);

}