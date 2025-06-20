package com.NewsAggregation.NewsApp.model;
import jakarta.persistence.*;

@Entity
public class NotificationConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int configId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    private boolean isBusinessEnabled;
    private boolean isEntertainmentEnabled;
    private boolean isSportsEnabled;
    private boolean isTechnologyEnabled;
    private String keywords;
}
