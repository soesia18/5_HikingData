package at.kaindorf.hiking.thread;

import at.kaindorf.hiking.data.Queue;
import at.kaindorf.hiking.data.Trk;
import jakarta.xml.bind.JAXB;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.List;

@AllArgsConstructor
public class Producer implements Runnable {

    private final String name;
    private final List<File> files;
    private Queue queue;

    @Override
    public void run() {
        files.forEach(file -> {
            System.out.println(name + " " + file.getName());
            Trk trk = readTrk(file);
            try {
                queue.enqueue(trk);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Trk readTrk (File file) {
        return JAXB.unmarshal(file, Trk.class);
    }
}
