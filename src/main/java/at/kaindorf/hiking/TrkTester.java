package at.kaindorf.hiking.data;

import at.kaindorf.hiking.bl.CalculateDistance;
import at.kaindorf.hiking.bl.CalculateElevation;
import at.kaindorf.hiking.thread.Consumer;
import at.kaindorf.hiking.thread.Producer;
import jakarta.xml.bind.JAXB;

import java.io.Console;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TrkTester {

    private static final int MAX_PRODUCERS = 4;
    public static void main(String[] args) {
        File hiking = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "hiking").toFile();

        List<File> files = Arrays.asList(Objects.requireNonNull(hiking.listFiles()));

        Queue queue = new Queue(5);

        /*Producer producer = new Producer("Producer", files, queue);

        Thread thread = new Thread(producer);
        thread.start();

        Consumer consumer = new Consumer("Consumer", queue, Arrays.asList(new CalculateElevation(),
                new CalculateDistance()));

        Thread thread1 = new Thread(consumer);
        thread1.start();*/


        for (int i = 0; i < MAX_PRODUCERS; i++) {
            Producer producer = new Producer("Producer " + i, files.subList(files.size() / MAX_PRODUCERS * (i),
                    files.size() / MAX_PRODUCERS * (i+1) - 1), queue);
            Thread thread = new Thread(producer);
            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer("Consumer " + i, queue, Arrays.asList(new CalculateElevation(),
                    new CalculateDistance()));
            Thread thread = new Thread(consumer);
            thread.start();
        }

    }
}
