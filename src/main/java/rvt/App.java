package rvt;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();
        UserInterface userInterface = new UserInterface(todoList, scanner);
        userInterface.start();
    }
}
