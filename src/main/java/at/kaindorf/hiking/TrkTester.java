package at.kaindorf.hiking;

import at.kaindorf.hiking.bl.CalculateDistance;
import at.kaindorf.hiking.bl.CalculateElevation;
import at.kaindorf.hiking.bl.LogObserver;
import at.kaindorf.hiking.bl.PrintObserver;
import at.kaindorf.hiking.data.Queue;
import at.kaindorf.hiking.thread.TrkConsumer;
import at.kaindorf.hiking.thread.TrkProducer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TrkTester {

    private static final int MAX_PRODUCERS = 4;
    private static final int MAX_CONSUMERS = 3;


    public static void main(String[] args) {
        File hiking = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "hiking").toFile();

        List<File> files = Arrays.asList(Objects.requireNonNull(hiking.listFiles()));

        Queue queue = new Queue(5, files.size());

        /*Producer producer = new Producer("Producer", files, queue);

        Thread thread = new Thread(producer);
        thread.start();

        Consumer consumer = new Consumer("Consumer", queue, Arrays.asList(new CalculateElevation(),
                new CalculateDistance()));

        Thread thread1 = new Thread(consumer);
        thread1.start();*/


        for (int i = 0; i < MAX_PRODUCERS; i++) {
            System.out.println("Producer " + i + " from " + files.size() / MAX_PRODUCERS * (i) + " to " + (files.size() / MAX_PRODUCERS * (i+1)));
            TrkProducer producer = new TrkProducer("Producer " + i, files.subList(files.size() / MAX_PRODUCERS * (i),
                    files.size() / MAX_PRODUCERS * (i+1)), queue);
            Thread thread = new Thread(producer);
            thread.start();
        }

        LogObserver logObserver = new LogObserver(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "out.log").toFile());
        PrintObserver printObserver = new PrintObserver();

        for (int i = 0; i < MAX_CONSUMERS; i++) {
            TrkConsumer consumer = new TrkConsumer("Consumer " + i, queue, Arrays.asList(new CalculateElevation(),
                    new CalculateDistance()));
            consumer.attach(logObserver);
            consumer.attach(printObserver);

            Thread thread = new Thread(consumer);
            thread.start();
        }

    }
}
