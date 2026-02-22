package rvt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TodoList {
    private ArrayList<String> list;
    private final String filePath = "data/todo.csv";

    public TodoList() {
        list = new ArrayList<>();
        loadFromFile(); // Call method to read tasks from the file
    }

    public void add(String task) {
        list.add(task); // Add the new task to the list
        try (PrintWriter pWriter = new PrintWriter(new FileWriter(filePath, true))) {
            pWriter.println(getLastId() + "," + task); // Save the new task to the csv file
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void print() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    private boolean updateFile() {
        try (PrintWriter pWriter = new PrintWriter(filePath)) {
            pWriter.println("id,task");
            for (int i = 0; i < list.size(); i++) { // Loop to write all current tasks
                pWriter.println((i + 1) + "," + list.get(i)); // Save each task with a new ID
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            return false;
        }
    }
 
    public void remove(int id) {
        list.remove(id - 1);
        updateFile();
    }

    private void loadFromFile() {
        try (Scanner reader = new Scanner(new File(filePath))) {
            reader.nextLine();
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String[] parts = row.split(","); // Read one line from the file
                String name = parts[1]; // Get only the task name "the task itself" part

                list.add(name); // Add the saved task to the list
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int getLastId() {
        return list.size();
    }

    public boolean checkEventString(String value) {
        // Check if string has only letters/numbers/spaces and is 3+ characters long
        if (Pattern.matches("^[a-zA-Z0-9 ]+$", value) && value.length() >= 3) {
            return true;
        } else {
            return false;
        }
    }
}