package rvt.ProductsAndCategories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:system.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        String categoriesTable = "CREATE TABLE IF NOT EXISTS categories ("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT NOT NULL)";

        String productsTable = "CREATE TABLE IF NOT EXISTS products ("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT NOT NULL,"
                + "price REAL NOT NULL,"
                + "category_id INTEGER,"
                + "FOREIGN KEY (category_id) REFERENCES categories(id))";
        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(categoriesTable);
            stmt.execute(productsTable);

        } catch (SQLException e) {
            throw new RuntimeException("Database init failed: "
             + e.getMessage());
        }
    }
}
