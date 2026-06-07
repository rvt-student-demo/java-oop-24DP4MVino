package rvt.StudentRegistration;

import java.util.ArrayList;

public class StudentData {
    private ArrayList<Students> list;
    private FileHandler fileHandler = new FileHandler();

    public StudentData() {
        this.list = fileHandler.loadFromFile();
    }

    public void register(Students students) {
        list.add(students);
        fileHandler.addToFile(students);
    }

    public void remove(String kods) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(kods)) {
                list.remove(i);
                fileHandler.updateFile(list);
                break;
            }
        }
    }

    public Students getStudentByCode(String kods) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(kods)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void edit(String kods, String jaunsVards, String jaunsUzvards, String jaunsEpasts) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(kods)) {
                Students students = list.get(i);
                students.setName(jaunsVards);
                students.setSurname(jaunsUzvards);
                students.setEmail(jaunsEpasts);
                fileHandler.updateFile(list);
                break;
            }
        }
    }

    public ArrayList<Students> getStudentList() {
        return this.list;
    }
}
