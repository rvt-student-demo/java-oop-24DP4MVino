package rvt.StudentRegistration;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class userInterface {
    private StudentData data;
    private Scanner scanner;
    
    public userInterface(StudentData data, Scanner scanner) {
        this.data = data;
        this.scanner = scanner;
    }

    public void start() {
        while(true) {
            System.out.println("Komandas: register, show, remove, edit, exit");
            System.out.print("Komanda: ");
            String command = scanner.nextLine();

            if (command.equals("register")) {
                System.out.println("Vards: ");
                String vards = scanner.nextLine();
                if (!Validator.isNameValid(vards)) {
                    System.out.println("Vardam jabut vismaz 3 simboliem garam un jasatur tikai burti!");
                    continue;
                }

                System.out.println("Uzvards: ");
                String uzvards = scanner.nextLine();
                if (!Validator.isSurnameValid(uzvards)) {
                    System.out.println("Uzvardam jabut vismaz 1 simbolam garam un jasatur tikai burti!");
                    continue;
                }

                System.out.println("E-pasts: ");
                String epasts = scanner.nextLine();
                if (!Validator.isEmailValid(epasts)) {
                    System.out.println("Nederigs e-pasta formats! (jasatur @ un punkts)");
                    continue;
                }

                System.out.println("Personas kods: ");
                String kods = scanner.nextLine();
                if (!Validator.isCodeValid(kods)) {
                    System.out.println("Nederigs personas koda formats! Jabut 12 simboliem (formats: 000000-00000)");
                    continue;
                }

                ZonedDateTime tagad = ZonedDateTime.now(ZoneId.systemDefault());
                String datums = tagad.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String laiks = tagad.format(DateTimeFormatter.ofPattern("HH:mm"));

                data.register(new Students(vards, uzvards, epasts, kods, datums, laiks));
                System.out.println("Students registrets!");
            } else if (command.equals("show")) {
                ArrayList<Students> allStudents = data.getStudentList();
                printCLITable(allStudents);
            } else if (command.equals("remove")) {
                System.out.print("Personas kods, ko dzest: ");
                String kods = scanner.nextLine();
                if (!Validator.isCodeValid(kods)) {
                    System.out.println("Nederigs personas koda formats! Jabut 12 simboliem (formats: 000000-00000)");
                    continue;
                }
                data.remove(kods);
                System.out.println("Students dzests.");
            } else if (command.equals("edit")) {
                System.out.print("Personas kods, ko rediget: ");
                String kods = scanner.nextLine();
                if (!Validator.isCodeValid(kods)) {
                    System.out.println("Nederigs personas koda formats! Jabut 12 simboliem (formats: 000000-00000)");
                    continue;
                }

                Students students = data.getStudentByCode(kods);
                if (students != null) {
                    System.out.println("Jaunais vards(atstaj tuksu, lai nemainitu): ");
                    String vardsInput = scanner.nextLine();
                    String newVards = students.getName();
                    if (!vardsInput.isEmpty()) {
                        if(Validator.isNameValid(vardsInput)){
                            newVards = vardsInput;
                        } else {
                            System.out.println("Vardam jabut vismaz 3 simboliem garam un jasatur tikai burti!");
                            continue;
                        }
                    }

                    System.out.println("Jaunais uzvards(atstaj tuksu, lai nemainitu): ");
                    String uzvardsInput = scanner.nextLine();
                    String newUzvards = students.getSurname();
                    if(!uzvardsInput.isEmpty()) {
                        if (Validator.isSurnameValid(uzvardsInput)) {
                            newUzvards = uzvardsInput;
                        } else {
                            System.out.println("Uzvardam jabut vismaz 1 simbolam garam un jasatur tikai burti!");
                            continue;
                        }
                    }

                    System.out.println("Jaunais e-pasts(atstaj tuksu, lai nemainitu): ");
                    String epastsInput = scanner.nextLine();
                    String newEpasts = students.getEmail();
                    if(!epastsInput.isEmpty()) {
                        if (Validator.isEmailValid(epastsInput)) {
                            newEpasts = epastsInput;
                        } else {
                            System.out.println("Nederigs e-pasta formats! (jasatur @ un punkts)");
                            continue;
                        }
                    }
                    data.edit(kods, newVards, newUzvards, newEpasts);
                    System.out.println("Dati atjauninati.");
                } else {
                    System.out.println("Students nav atrasts.");
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Nepareiza komanda.");
            }
        }
    }

    private void printCLITable(ArrayList<Students> studentsList) {
        if (studentsList.isEmpty()) {
            System.out.println("Reģistra nav neviena studenta.");
            return;
        }

        String template = "| %-12s | %-15s | %-25s | %-15s | %-12s | %-8s |%n";
        printLine();
        System.out.printf(template, "Vards", "Uzvards", "E-pasts", "Pers.kods", "Datums", "Laiks");
        printLine();

        for (Students student : studentsList) {
            System.out.printf(template,
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getCode(),
                student.getDate(),
                student.getTime()
            );
        }
        printLine();
    }

    private void printLine() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(17) + "+" + "-".repeat(27) + "+" + "-".repeat(17) + "+" + "-".repeat(14) + "+" + "-".repeat(10) + "+");
    }
}
