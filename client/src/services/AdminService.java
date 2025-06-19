package services;
import model.*;
import java.util.*;
public class AdminService {
    public void addCategory() {

    }
    public void removeCategory() {

    }
    public void addKeyword() {

    }
    public void removeKeyword() {

    }

    public void removeUser(List<User> users, String userId) {
        users.removeIf(user -> user.id.equals(userId));
    }

    public void giveAdminAccess(User user) {
        user.isAdmin = true;
    }

    public void revokeAdminAccess(User user) {
        user.isAdmin = false;
    }

    public void updateNewsProviderStatus(NewsSource source, String status) {
        source.status = status;
    }

    public void updateApiKey(NewsSource source, String newKey) {
        source.apiKey = newKey;
    }

    public void viewNewsProviderStatus(NewsSource source) {
        System.out.println("Provider: " + source.name + " Status: " + source.status);
    }
}
