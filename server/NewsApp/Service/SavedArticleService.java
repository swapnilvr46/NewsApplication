//package com.NewsAggregation.NewsApp.service;
//import com.NewsAggregation.NewsApp.model.SavedArticle;
//import com.NewsAggregation.NewsApp.repository.SavedArticleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//import java.util.List;
//public class SavedArticleService {
//    private final SavedArticleRepository savedArticleRepository;
//
//    @Autowired
//    public SavedArticleService(SavedArticleRepository savedArticleRepository) {
//        this.savedArticleRepository = savedArticleRepository;
//    }
//
//    public List<SavedArticle> getArticlesByUserId(int userId) {
//        return savedArticleRepository.findByUser_Id(userId);
//    }
//}
