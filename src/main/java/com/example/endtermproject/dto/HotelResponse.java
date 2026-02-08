package com.example.endtermproject.dto;

public class HotelResponse {
    private Long id;
    private String name;
    private String city;
    private int stars;

    public HotelResponse(Long id, String name, String city, int stars) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getStars() { return stars; }
}
