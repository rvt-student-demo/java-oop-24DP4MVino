package rvt.StudentRegistration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String filePath = "data/students.csv";

    public void addToFile(Students students) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter(filePath, true))) {
            pWriter.println(students.toCsv());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public boolean updateFile(ArrayList<Students> list) {
        try (PrintWriter pWriter = new PrintWriter(filePath)) {
            for (int i = 0; i < list.size(); i++) {
                pWriter.println(list.get(i).toCsv());
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            return false;
        }
    }

    public ArrayList<Students> loadFromFile() {
        ArrayList<Students> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] parts = row.split(",");
                if (parts.length == 6) {
                    list.add(new Students(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
}
