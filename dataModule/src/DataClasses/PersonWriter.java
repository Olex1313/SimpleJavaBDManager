package DataClasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PersonWriter {
    private FileOutputStream outputStream;

    public PersonWriter(FileOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeJSON(Human human) {
        try
        {
            String formedJSON = getJSONString(human);

            if (formedJSON == null)
                throw new NullPointerException();

            this.outputStream.write(formedJSON.getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static String getJSONString(Human human) {
        if (human instanceof Student) {
            Student student = (Student) human;
            return "{" +
                    "\"firstName\":" + '"' + student.getFirstName() + '"'  +
                    ", \"lastName\":" + '"' + student.getLastName() + '"' +
                    ", \"age\":" + '"' + student.getAge() + '"' +
                    ", \"specialization\":" + '"' + student.getSpecialization() + '"' +
                    ", \"groupNumber\":" + '"' + student.getGroupNumber() + '"' +
                    "}\n";
        }
        if (human instanceof Human) {
            return "{" +
                    "\"firstName\":" + '"' + human.getFirstName() + '"'  +
                    ", \"lastName\":" + '"' + human.getLastName() + '"' +
                    ", \"age\":" + '"' + human.getAge() + '"' +
                    "}\n";
        }
        return null;
    }
}
