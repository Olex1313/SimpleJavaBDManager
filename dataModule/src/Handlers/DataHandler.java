package Handlers;

import DataClasses.Human;
import DataClasses.PersonWriter;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private List<Human> buffer;

    public DataHandler() {
        this.buffer = new ArrayList<>();
    }

    public void addRecord(Human human) {
        buffer.add(human);
        System.out.println("Record added");
    }

    public void removeRecord(Human human) {
        buffer.remove(human);
    }

    public void printBuffer() {
        for (Human human : buffer) {
            System.out.println(human);
        }
    }

    public void writeJSON(String filename) {
        PersonWriter.writeJSON(buffer, filename);
    }

}
