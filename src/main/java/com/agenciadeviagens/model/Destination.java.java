package com.agenciadeviagens.model;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private String name;
    private String location;
    private String description;
    private double rating;
    private int ratingCount;

    public Destination(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.rating = 0.0;
        this.ratingCount = 0;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }
    
    // Método para calcular a média de avaliação
    public void updateRating(int newRating) {
        double totalRating = this.rating * this.ratingCount + newRating;
        this.ratingCount++;
        this.rating = totalRating / this.ratingCount;
    }
}
