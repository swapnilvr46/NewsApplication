package model;
import java.util.*;
public class NotificationConfig {
    public String id;
    public String userId;
    public List<String> categoryPreference = new ArrayList<>();
    public List<String> keywords = new ArrayList<>();

    public void addCategory(String category) { categoryPreference.add(category); }
    public void removeCategory(String category) { categoryPreference.remove(category); }
    public void addKeyword(String keyword) { keywords.add(keyword); }
    public void removeKeyword(String keyword) { keywords.remove(keyword); }
}
