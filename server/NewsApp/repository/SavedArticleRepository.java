package com.NewsAggregation.NewsApp.repository;

import com.NewsAggregation.NewsApp.model.SavedArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedArticleRepository extends JpaRepository<SavedArticle, Integer> {
    List<SavedArticle> findByUser_UserId(int userId);
}
