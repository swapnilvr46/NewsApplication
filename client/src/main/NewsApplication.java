package main;
import model.*;
import services.*;
import java.util.*;
public class NewsApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        NewsService newsService = new NewsService();

        List<User> users = new ArrayList<>();
        List<NewsArticle> articles = new ArrayList<>();
        newsService.newArticles = articles;

        System.out.println("Welcome to News Aggregator!");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = new User();
                user.id = UUID.randomUUID().toString();
                user.userName = username;
                user.email = email;
                user.password = password;
                user.isAdmin = false;
                users.add(user);

                System.out.println("Registration successful.");

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User loggedInUser = null;
                for (User user : users) {
                    if (user.email.equals(email) && user.password.equals(password)) {
                        loggedInUser = user;
                        break;
                    }
                }

                if (loggedInUser != null) {
                    System.out.println("Login successful. Welcome " + loggedInUser.userName + "!");

                    if (loggedInUser.isAdmin) {
                        System.out.println("\nAdmin Panel:");
                        System.out.println("1. Add Category\n2. View News Provider Status\n3. Logout");
                        int adminOption = scanner.nextInt();
                        scanner.nextLine();

                        if (adminOption == 1) {
                            System.out.print("Enter category to add: ");
                            String category = scanner.nextLine();
                            NotificationConfig config = new NotificationConfig();
                            config.userId = loggedInUser.id;
                            config.addCategory(category);
                            loggedInUser.notificationConfigs.add(config);
                            System.out.println("Category added.");
                        } else if (adminOption == 2) {
                            NewsSource newsSource = new NewsSource();
                            newsSource.name = "NewsAPI";
                            newsSource.status = "Active";
                            adminService.viewNewsProviderStatus(newsSource);
                        }

                    } else {
                        System.out.println("\nUser Panel:");
                        System.out.println("1. Add Keyword\n2. View News by Category\n3. Logout");
                        int userOption = scanner.nextInt();
                        scanner.nextLine();

                        if (userOption == 1) {
                            System.out.print("Enter keyword: ");
                            String keyword = scanner.nextLine();
                            NotificationConfig cfg = new NotificationConfig();
                            cfg.userId = loggedInUser.id;
                            cfg.addKeyword(keyword);
                            loggedInUser.notificationConfigs.add(cfg);
                            System.out.println("Keyword added.");
                        } else if (userOption == 2) {
                            System.out.print("Enter category: ");
                            String cat = scanner.nextLine();
                            List<NewsArticle> catNews = newsService.getNewsByCategory(cat);
                            System.out.println("Found " + catNews.size() + " articles.");
                        }
                    }

                } else {
                    System.out.println("Invalid login.");
                }

            } else if (choice == 3) {
                System.out.println("Thank you for using News Aggregator. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
