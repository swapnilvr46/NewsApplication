package com.NewsAggregation.NewsApp.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SavedArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int savedArticleId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "articleId")
    private NewsArticle article;
    private LocalDateTime savedAt;
}
