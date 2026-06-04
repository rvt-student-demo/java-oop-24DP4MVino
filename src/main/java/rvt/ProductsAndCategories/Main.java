package rvt.ProductsAndCategories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseConnection.initDatabase();

        while (true) {
            System.out.println("\n--- IZVELNE ---");
            System.out.println("1 - Pievienot kategoriju");
            System.out.println("2 - Pievienot produktu");
            System.out.println("3 - Paradit visas kategorijas");
            System.out.println("4 - Paradit visus produktus");
            System.out.println("5 - Meklet produktus pec kategorijas");
            System.out.println("0 - Iziet");
            System.out.print("Izvelies darbibu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCategory();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    showAllCategories();
                    break;
                case "4":
                    showAllProducts();
                    break;
                case "5":
                    searchProductsByCategory();
                    break;
                case "0":
                    System.out.println("Programma aptureta.");
                    return;
                default:
                    System.out.println("Nederiga izvele! Megini velreiz.");
            }
        }
    }

    private static void addCategory() {
        System.out.print("Ievadi kategorijas nosaukumu: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Kategorijas nosaukums nevar but tukss!");
            return;
        }

        String sql = "INSERT INTO categories (name) VALUES (?);";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("Kategorija '" + name + "' veiksmigi saglabata!");
            
        } catch (SQLException e) {
            System.out.println("Kluda, pievienojot kategoriju: " + e.getMessage());
        }
    }

    private static void addProduct() {
        System.out.println("\nPieejamas kategorijas:");
        showAllCategories();
        
        System.out.print("Ievadi produkta nosaukumu: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Ievadi cenu: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                System.out.println("Cena nevar but negativa!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Kluda: Jaievada skaitlis!");
            return;
        }

        System.out.print("Ievadi kategorijas ID no saraksta: ");
        int categoryId;
        try {
            categoryId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Kluda: ID jabut veselam skaitlim!");
            return;
        }

        String sql = "INSERT INTO products (name, price, category_id) VALUES (?, ?, ?);";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, categoryId);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produkts '" + name + "' veiksmigi pievienots datubazei!");
            }
            
        } catch (SQLException e) {
            System.out.println("Kluda, pievienojot produktu: " + e.getMessage());
        }
    }

    private static void showAllCategories() {
        String sql = "SELECT id, name FROM categories;";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                Category cat = new Category(rs.getInt("id"), rs.getString("name"));
                System.out.println(cat);
            }

            if (!hasData) {
                System.out.println("(Saraksts ir tukss)");
            }

        } catch (SQLException e) {
            System.out.println("Kluda, ielasot kategorijas: " + e.getMessage());
        }
    }

    private static void showAllProducts() {
        String sql = "SELECT id, name, price, category_id FROM products;";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                Product prod = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("category_id")
                );
                System.out.println(prod);
            }

            if (!hasData) {
                System.out.println("(Saraksts ir tukss)");
            }

        } catch (SQLException e) {
            System.out.println("Kluda, ielasot produktus: " + e.getMessage());
        }
    }

    private static void searchProductsByCategory() {
        System.out.print("Ievadi kategorijas ID meklesanai: ");
        int searchId;
        try {
            searchId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Kluda: Jaievada vesels skaitlis (kategorijas ID)!");
            return;
        }

        String sql = "SELECT id, name, price, category_id FROM products WHERE category_id = ?;";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, searchId);

            try (ResultSet rs = ps.executeQuery()) {
                boolean hasData = false;
                while (rs.next()) {
                    hasData = true;
                    Product prod = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("category_id")
                    );
                    System.out.println(prod);
                }

                if (!hasData) {
                    System.out.println("Netika atrasts neviens produkts ar sadu kategorijas ID.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Kluda, meklejot produktus: " + e.getMessage());
        }
    }
}