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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((borrowedBy == null) ? 0 : borrowedBy.hashCode());
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
        //Storage other = (Storage) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (borrowedBy == null) {
            if (other.borrowedBy != null)
                return false;
        } else if (!borrowedBy.equals(other.borrowedBy))
            return false;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
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
        return "Storage [id=" + id + ", borrowedBy=" + borrowedBy + ", itemID=" + itemId + ", borrowDate="
                + borrowDate + ", dueDate=" + dueDate + "]";
    }
}