package main;
import model.*;
import services.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
public class NewsApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        NewsArticleService newsArticleService = new NewsArticleService();
        List<NewsArticle> articles = new ArrayList<>();
        newsArticleService.newArticles = articles;

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
                System.out.print("Enter role (USER or ADMIN): ");
                String role = scanner.nextLine().toUpperCase();
                try {
                    String signupPayload = String.format("{\"username\":\"%s\", \"email\":\"%s\", \"password\":\"%s\", \"role\":\"%s\"}",
                            username, email, password, role);
                    URL url = new URL("http://localhost:8080/api/auth/signup");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setDoOutput(true);
                    try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                        outputStream.write(signupPayload.getBytes());
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);
                    Scanner responseScanner ;
                    if (responseCode >= 200 && responseCode < 300) {
                        responseScanner = new Scanner(httpURLConnection.getInputStream());
                    } else {
                        responseScanner = new Scanner(httpURLConnection.getErrorStream());
                    }
                    while (responseScanner.hasNextLine()) {
                        System.out.println(responseScanner.nextLine());
                    }
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    System.err.println("Error during signup: " + e.getMessage());
                }

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter Basic Auth username: ");
                String authUsername = scanner.nextLine();
                System.out.print("Enter Basic Auth password: ");
                String authPassword = scanner.nextLine();

                try {
                    String loginPayload = String.format("{\"email\":\"%s\", \"password\":\"%s\"}"
                            ,email, password);
                    URL url = new URL("http://localhost:8080/api/auth/login");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    String basicAuth = Base64.getEncoder().encodeToString((authUsername + ":" + authPassword).getBytes());
                    httpURLConnection.setRequestProperty("Authorization", "Basic " + basicAuth);
                    httpURLConnection.setDoOutput(true);
                    try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                        outputStream.write(loginPayload.getBytes());
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    Scanner responseScanner = new Scanner(httpURLConnection.getInputStream());
                    while (responseScanner.hasNextLine()) {
                        System.out.println(responseScanner.nextLine());
                    }
                    httpURLConnection.disconnect();

                } catch (Exception e) {
                    System.err.println("Error during login: " + e.getMessage());
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
