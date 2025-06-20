package services;

import model.NewsArticle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsArticleService {
    public List<NewsArticle> newArticles = new ArrayList<>();
    public Date date;

    public List<NewsArticle> getNews() { return newArticles; }

    public List<NewsArticle> getNewsByCategory(String category) {
        List<NewsArticle> result = new ArrayList<>();
        for (NewsArticle article : newArticles) {
            if (article.category.equalsIgnoreCase(category)) {
                result.add(article);
            }
        }
        return result;
    }

    public List<NewsArticle> getNewsByRange(Date start, Date end) {
        List<NewsArticle> result = new ArrayList<>();
        for (NewsArticle article : newArticles) {
            if (!article.date.before(start) && !article.date.after(end)) {
                result.add(article);
            }
        }
        return result;
    }
}
