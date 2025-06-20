package com.NewsAggregation.NewsApp.repository;

import com.NewsAggregation.NewsApp.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
