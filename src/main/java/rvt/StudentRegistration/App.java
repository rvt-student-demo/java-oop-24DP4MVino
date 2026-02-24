package rvt.StudentRegistration;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentData data = new StudentData();
        userInterface ui = new userInterface(data, scanner);
        ui.start();
    }
}
