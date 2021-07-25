package Handlers;

import DataClasses.Human;
import DataClasses.PersonWriter;
import DataClasses.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BufferManager {
    private List<Human> buffer;
    private BufferedReader reader;

    public BufferManager() {
        this.buffer = new ArrayList<>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addRecord() {
        try
        {
            Human human = this.readRecord();
            if (human != null)
                this.buffer.add(human);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBuffer() {
        System.out.println("Printing buffer:");
        for (Human human : this.buffer)
            System.out.println(human);
    }

    public void removeRecord(Human human) {
        System.out.println("Removing record:");
        System.out.println(human);
        this.buffer.remove(human);
        System.out.printf("Record removed");
    }

    public void recordBufferToJSON() {
        System.out.println("Input filename of JSON file to save as");
        try {
            String filename = reader.readLine();
            PersonWriter.writeJSON(this.buffer, filename);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Human readRecord() throws IOException{
        Human human = null;
        System.out.println("Do you want to input Human?");
        System.out.println("y/n");
        String answer = reader.readLine();
        if (answer.equals("y")) {
            System.out.println("Input first name");
            String firstName = reader.readLine();
            System.out.println("Input last name");
            String lastName = reader.readLine();
            System.out.println("Input age");
            Integer age = Integer.parseInt(reader.readLine());
            return new Human(firstName, lastName, age);
        }
        System.out.println("Do you want to input Student?");
        System.out.println("y/n");
        answer = reader.readLine();
        if (answer.equals("y")) {
            System.out.println("Input first name");
            String firstName = reader.readLine();
            System.out.println("Input last name");
            String lastName = reader.readLine();
            System.out.println("Input age");
            Integer age = Integer.parseInt(reader.readLine());
            System.out.println("Input specialization");
            String spec = reader.readLine();
            System.out.println("Input group number");
            Integer group = Integer.parseInt(reader.readLine());
            return new Student(firstName, lastName, age, spec, group);
        }
        System.out.println("You haven't specified input, returning null...");
        return null;
    }
}
