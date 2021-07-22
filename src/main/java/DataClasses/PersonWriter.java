package DataClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.*;

public class PersonWriter {
    public static void writeJSON(Human human, String filename) {
        try {
            if (human == null)
                throw new NullPointerException();

            String formedJSON = new JSONObject(human).toString();
            FileWriter writer = new FileWriter(filename);
            writer.write(formedJSON);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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

            JSONArray array = new JSONArray();

            for (Human human : humans)
                array.put(new JSONObject(human));

            writer.write(array.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}