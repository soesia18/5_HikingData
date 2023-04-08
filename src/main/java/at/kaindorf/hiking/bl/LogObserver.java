package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class LogObserver implements Observer {

    private final File file;

    private ObjectMapper mapper = new ObjectMapper();
    private JsonGenerator generator;
    private FileWriter fileWriter;

    public LogObserver(File file) {
        this.file = file;
        try {
            file.delete();
            fileWriter = new FileWriter(file, true);
            this.generator = mapper.getFactory().createGenerator(new FileOutputStream(file, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Result result) {
        synchronized (file) {
            try {
                mapper.writeValue(generator, result);
                fileWriter.write(System.lineSeparator());
                fileWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
