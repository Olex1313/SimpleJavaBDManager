import DataClasses.*;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class TestStructures {
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("list.json")));
        String read = reader.readLine();
        Human readHuman = PersonReader.readJSONHumanString(read);
        System.out.println(readHuman);
    }
}
