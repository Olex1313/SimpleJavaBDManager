package DataClasses;

import java.io.OutputStream;

public class PersonWriter implements Writer {
    private OutputStream outputStream;

    public PersonWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
