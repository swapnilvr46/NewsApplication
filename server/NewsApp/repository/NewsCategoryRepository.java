package com.NewsAggregation.NewsApp.repository;

import com.NewsAggregation.NewsApp.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {

}