package com.juszkiewicz.additionaltask.additionaltask.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "You have to provide the title of the book!")
    private String title;
    @Column(name = "author")
    @NotBlank(message = "You have to provide the author of the book!")
    private String author;
    @Column(name = "publicationYear")
    @NotBlank(message = "You have to provide a year!")
    private int publicationYear;
    @Column(name = "rating")
    private double rating;
    @Column(name = "ratingCount")
    private int ratingCount;

    @Column(name = "ratingPoints")
    private int ratingPoints;

    public Book() {
    }

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(int ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    @PrePersist
    protected void onCreate() {
        this.rating=0;
        this.ratingCount=0;
        this.ratingPoints=0;
    }
}
