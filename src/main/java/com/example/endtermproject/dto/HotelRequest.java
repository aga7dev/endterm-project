package com.example.endtermproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class HotelRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @Min(1) @Max(5)
    private int stars;

    public String getName() { return name; }
    public String getCity() { return city; }
    public int getStars() { return stars; }

    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setStars(int stars) { this.stars = stars; }
}
