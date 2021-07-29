package DataClasses;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PersonReader {
    public static JSONArray readJSON(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");
            return new JSONArray(str);
        }
        catch (IOException e) {
            System.out.println("Got IOException");
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Human> processJSON(JSONArray jsonArray) {
        ArrayList<Human> result = new ArrayList<Human>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if (obj.length() == 3)
                result.add(new Human(
                        (String) obj.get("firstName"),
                        (String) obj.get("lastName"),
                        (Integer) obj.get("age"))
                );
            else if (obj.length() == 5)
                result.add(new Student(
                        (String) obj.get("firstName"),
                        (String) obj.get("lastName"),
                        (Integer) obj.get("age"),
                        (String) obj.get("specialization"),
                        (Integer) obj.get("groupNumber"))
                );
        }
        return result;
    }

    public static ArrayList<Human> processJSON(String path) {
        ArrayList<Human> result = processJSON(readJSON(path));
        return result;
    }
}
