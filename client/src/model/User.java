package model;
import java.util.*;
public class User {
    public String id;
    public String userName;
    public String email;
    public String password;
    public boolean isAdmin;
    public List<SavedArticle> savedArticles = new ArrayList<>();
    public List<NotificationConfig> notificationConfigs = new ArrayList<>();

    public void changePassword() {

    }
    public void updateProfile() {

    }
    public User getUser() {
        return this;
    }
}
