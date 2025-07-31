package com.NewsAggregation.NewsApp.repository;
import com.NewsAggregation.NewsApp.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
public interface NewsArticleRepository extends JpaRepository<NewsArticle, Integer> {
    List<NewsArticle> findByPublishedDate(LocalDate date);
    List<NewsArticle> findByCategory_CategoryName(String categoryName);
}
