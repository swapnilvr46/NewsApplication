package com.NewsAggregation.NewsApp.repository;
import com.NewsAggregation.NewsApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Integer> {
}
