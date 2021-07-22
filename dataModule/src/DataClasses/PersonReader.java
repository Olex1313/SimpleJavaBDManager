package DataClasses;

import java.util.ArrayList;
import java.util.List;

public class PersonReader {
    public static Human readJSONHumanString(String string) {
        Human human = new Human();
        String buffer = "";
        Boolean isInAttribute = false;
        List<String> attributes = new ArrayList<>();
        for (Character ch : string.toCharArray()) {
            if (ch.equals(':')) {
                isInAttribute = true;
            }
            else if (ch.equals(',') || ch.equals('}')) {
                isInAttribute = false;
                attributes.add(buffer);
                buffer = "";
            }
            else if (isInAttribute) {
                buffer += ch;
            }
        }
        if (attributes.size() == 3) {
            String fName = removeFirstAndLast(attributes.get(0));
            String lName = removeFirstAndLast(attributes.get(1));
            Integer age = Integer.parseInt(removeFirstAndLast(attributes.get(2)));
            return new Human(fName, lName, age);
        }
        return null;
    }

    private static String removeFirstAndLast(String str)
    {

        StringBuilder sb = new StringBuilder(str);
        sb.deleteCharAt(str.length() - 1);
        sb.deleteCharAt(0);
        return sb.toString();
    }

}
