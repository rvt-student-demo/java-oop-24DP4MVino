package rvt;

import java.util.Scanner;

public class UserInterface {
    private TodoList todoList;
    private Scanner scanner;

    public UserInterface(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {
        while(true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("add")) {
                System.out.print("To add: ");
                String addTask = scanner.nextLine();
                if (todoList.checkEventString(addTask)) { // Проверяем перед добавлением
                    todoList.add(addTask);
                } else {
                    System.out.println("error");
                }
            } else if (command.equals("list")) {
                todoList.print();
            } else if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                int removedTask = Integer.valueOf(scanner.nextLine());
                todoList.remove(removedTask);
            } else if (command.equals("stop")) {
                break;
            }
        }
    }
}
