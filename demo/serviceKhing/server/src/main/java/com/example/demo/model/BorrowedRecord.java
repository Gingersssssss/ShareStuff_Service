package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class BorrowedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private Long borrowedBy;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    // Default constructor
    public BorrowedRecord() {}

    // Parameterized constructor
    public BorrowedRecord(Long borrowedBy, Long itemId, LocalDate borrowDate, LocalDate dueDate) {
        this.borrowedBy = borrowedBy;
        this.itemId = itemId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Long borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Method to calculate remaining days before due date
    public long getRemainingDays() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }

    // Method to check if the item is overdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(borrowDate) && LocalDate.now().isAfter(dueDate);
    }
    
    // Method to check if the item is borrowed by a specific user
    public boolean isBorrowedBy(Long userId) {
        return this.borrowedBy.equals(userId);
    }
}