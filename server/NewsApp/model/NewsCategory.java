package com.NewsAggregation.NewsApp.model;
import jakarta.persistence.*;

@Entity
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;
}
