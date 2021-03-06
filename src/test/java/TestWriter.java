import DataClasses.Human;
import DataClasses.PersonWriter;
import DataClasses.Student;

import java.util.ArrayList;
import java.util.List;

public class TestWriter {
    public static void main(String[] args) {
        Human human = new Human("SOme", "Body", 7);
        Student student = new Student("Some", "Student", 20, "ICT", 201);
        Student student2 = new Student("Some", "Student", 20, "ICT", 201);
        System.out.println(student);
        System.out.println(human);
        System.out.println(student.equals(student2));
        Human student3 = (Human) student2;
        System.out.println(student3);
        PersonWriter.writeJSON(student3, "text.txt");
        PersonWriter.writeJSON(student2, "text.json");
        List<Human> list = new ArrayList<>();
        list.add(human);
        PersonWriter.writeJSON(list, "list.json");
    }
}
