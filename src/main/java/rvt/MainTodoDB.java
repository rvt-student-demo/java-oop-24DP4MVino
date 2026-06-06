package rvt;

public class MainTodoDB {
    public static void main(String[] args) {
        TodoDB db = new TodoDB();

        System.out.println("Adding new tasks");
        db.add("Buy groceries");
        db.add("Finish homework");
        db.add("Go to the gym");
        db.add("Feed the cat");
        System.out.println("Tasks have been added to the database!\n");

        System.out.println("Finding all tasks");
        db.findAll();
        System.out.println();

        System.out.println("Removing by id");
        db.removeById(2);
        System.out.println("Task has been removed\n");

        System.out.println("Tasks after removing");
        db.findAll();
    }
}
