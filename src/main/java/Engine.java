import Handlers.BufferManager;
import Handlers.DataBaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private BufferManager bufferManager;
    private DataBaseManager dataBaseManager;
    public BufferedReader reader;

    public Engine(String DataBaseURL, String user, String pass) throws Exception {
        this.bufferManager = new BufferManager();
        this.dataBaseManager = new DataBaseManager(DataBaseURL, user, pass);
        if (!this.dataBaseManager.isConnected()) {
            throw new Exception("No DB connection");
        }
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, this is simple DB manager");
        Engine engine = new Engine("jdbc:postgresql://127.0.0.1:5432/javatest",
                "javauser",
                "javauser"
        );
        boolean cycle = true;
        while (cycle) {
            System.out.println("Please select operation:");
            System.out.println("1 - Buffer operations");
            System.out.println("2 - Database operation");
            System.out.println("3 - Exit program");
            int ans = engine.getAnswer();
            switch (ans) {
                case 1:
                    System.out.println("Select buffer operation:");
                    System.out.println("1 - Add record to buffer");
                    System.out.println("2 - Remove record from buffer");
                    System.out.println("3 - Show buffer");
                    System.out.println("4 - Dump buffer to JSON");
                    System.out.println("5 - Get back");
                    int bufAns = engine.getAnswer();
                    switch (bufAns) {
                        case 1:
                            engine.bufferManager.addRecord();
                            break;
                        case 2:
                            engine.bufferManager.removeRecord();
                            break;
                        case 3:
                            engine.bufferManager.printBuffer();
                            break;
                        case 4:
                            engine.bufferManager.recordBufferToJSON();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid option selected, try again");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Select database operation:");
                    System.out.println("1 - Load JSON to Database as new table");
                    System.out.println("2 - Print existing table");
                    System.out.println("3 - Get Back");
                    int dbAns = engine.getAnswer();
                    switch (dbAns) {
                        case 1:
                            engine.dataBaseManager.loadFromJSON();
                            break;
                        case 2:
                            engine.dataBaseManager.printAllRecords();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid option selected, try again");
                            break;
                    }
                    break;
                case 3:
                    cycle = false;
                    break;
            }
        }
    }

    public Integer getAnswer() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid number, returning 0");
            return 0;
        }
    }

}
