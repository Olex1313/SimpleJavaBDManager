package DataClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersonWriter {
    public static void writeJSON(Human human, String filename) {
        try {
            String formedJSON = PersonWriter.getJSONString(human);

            if (formedJSON == null)
                throw new NullPointerException();

            FileWriter writer = new FileWriter(filename);
            writer.write(formedJSON);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void writeJSON(List<Human> humans, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            if (humans.isEmpty()) {
                writer.write("[]");
                writer.close();
                return;
            }
            writer.write('[');
            String tmp = null;
            for (int i = 0; i < humans.size() - 1; i++) {
                tmp = PersonWriter.getJSONString(humans.get(i));
                if (tmp == null)
                    continue;

                writer.write(tmp + ", ");
            }
            tmp = PersonWriter.getJSONString(humans.get(humans.size() - 1));
            writer.write(tmp + "]");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static String getJSONString(Human human) {
        if (human instanceof Student) {
            Student student = (Student) human;
            return "{" +
                    "\"firstName\":" + '"' + student.getFirstName() + '"' +
                    ", \"lastName\":" + '"' + student.getLastName() + '"' +
                    ", \"age\":" + '"' + student.getAge() + '"' +
                    ", \"specialization\":" + '"' + student.getSpecialization() + '"' +
                    ", \"groupNumber\":" + '"' + student.getGroupNumber() + '"' +
                    "}";
        }
        if (human instanceof Human) {
            return "{" +
                    "\"firstName\":" + '"' + human.getFirstName() + '"' +
                    ", \"lastName\":" + '"' + human.getLastName() + '"' +
                    ", \"age\":" + '"' + human.getAge() + '"' +
                    "}";
        }
        return null;
    }
}
