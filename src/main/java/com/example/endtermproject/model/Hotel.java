package com.example.endtermproject.model;


import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private int stars; // 1..5

    public Hotel() {}

    public Hotel(String name, String city, int stars) {
        this.name = name;
        this.city = city;
        this.stars = stars;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getStars() { return stars; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setStars(int stars) { this.stars = stars; }
}
