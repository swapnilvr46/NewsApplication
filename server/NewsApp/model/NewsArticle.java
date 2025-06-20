package com.NewsAggregation.NewsApp.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;
    private String title;
    private String content;
    private String source;
    private String url;
    private LocalDate publishedDate;
    private int likes;
    private int dislikes;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private NewsCategory category;
    //@ManyToOne
   // @JoinColumn(name = "externalServerId")
    //private ExternalNewsServer externalServer;
}
