package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolDetail;
    private String ownerName;
    private String locationName;
    private Boolean borrowed;
    private String borrowerName;

    // Default constructor
    public Storage() {}

    // Parameterized constructor
    public Storage(String toolDetail, String ownerName, String locationName, Boolean borrowed, String borrowerName) {
        this.toolDetail = toolDetail;
        this.ownerName = ownerName;
        this.locationName = locationName;
        this.borrowed = borrowed;
        this.borrowerName = borrowerName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolDetail() {
        return toolDetail;
    }

    public void setToolDetail(String toolDetail) {
        this.toolDetail = toolDetail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", toolDetail='" + toolDetail + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", borrowed=" + borrowed +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}