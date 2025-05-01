package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolDetail;
    private String ownerName;
    private String locationName;
    private boolean borrowed;

    public Storage() {}

    public Storage(String toolDetail, String ownerName, String locationName, boolean borrowed) {
        this.toolDetail = toolDetail;
        this.ownerName = ownerName;
        this.locationName = locationName;
        this.borrowed = borrowed;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getToolDetail() { return toolDetail; }
    public void setToolDetail(String toolDetail) { this.toolDetail = toolDetail; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public boolean isBorrowed() { return borrowed; }
    public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }
}
