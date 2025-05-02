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

    // Method to check if the item is overdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(borrowDate) && LocalDate.now().isAfter(dueDate);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((borrowerId == null) ? 0 : borrowerId.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((borrowDate == null) ? 0 : borrowDate.hashCode());
        result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());

        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Storage other = (Storage) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (borrowerId == null) {
            if (other.borrowerId != null)
                return false;
        } else if (!borrowerId.equals(other.borrowerId))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (borrowDate == null) {
            if (other.borrowDate != null)
                return false;
        } else if (!borrowDate.equals(other.borrowDate))
            return false;
        if (dueDate == null) {
            if (other.dueDate != null)
                return false;
        } else if (!dueDate.equals(other.dueDate))
            return false;
    
        return true;
    }
    
    @Override
    public String toString() {
        return "Storage [id=" + id + ", borrowerId=" + borrowerId + ", item=" + item + ", borrowDate="
                + borrowDate + ", dueDate=" + dueDate + "]";
    }
}