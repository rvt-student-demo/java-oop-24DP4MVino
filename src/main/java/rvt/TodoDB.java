package rvt;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TodoDB {
    
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema(){
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY,"
                + "task TEXT NOT NULL) STRICT";
        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: "
             + e.getMessage());
        }
    }

    public void add(String task) {
        String sql = "INSERT INTO todo (task) VALUES (?)";

        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, task);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add task: "
             + e.getMessage());
        }
    }

    public void findAll() {
        String sql = "SELECT id, task FROM todo";

        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        )  {
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");

                System.out.println(id + " | " + task);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch tasks: "
             + e.getMessage());
        }
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        
        try (
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql)
        )  {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove task: "
             + e.getMessage());
        }
    }
}
