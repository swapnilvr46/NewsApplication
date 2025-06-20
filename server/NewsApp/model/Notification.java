package com.NewsAggregation.NewsApp.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "articleId")
    private NewsArticle article;
    private String message;
    private LocalDateTime sentAt;
}
