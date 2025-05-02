package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class BorrowedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long borrowerId;

    @ManyToOne
    private Item item;

    private LocalDate borrowDate;
    private LocalDate dueDate;

    // Default constructor
    public BorrowedRecord() {}

    // Parameterized constructor
    public BorrowedRecord(Long borrowerId, Item item, LocalDate borrowDate, LocalDate dueDate) {
        this.borrowerId = borrowerId;
        this.item = item;
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

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
}