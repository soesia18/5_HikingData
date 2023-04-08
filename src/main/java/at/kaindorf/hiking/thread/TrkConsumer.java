package at.kaindorf.hiking.thread;

import at.kaindorf.hiking.bl.CalculateElevation;
import at.kaindorf.hiking.bl.Strategy;
import at.kaindorf.hiking.data.Queue;
import at.kaindorf.hiking.data.Result;
import at.kaindorf.hiking.data.Trk;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class Consumer implements Runnable {

    private final String name;
    private Queue queue;

    private List<Strategy> strategies;
    @Override
    public void run() {
        while (true) {
            try {
                long startTime = System.nanoTime();
                Trk trk = queue.dequeue();
                Result result = new Result(trk.getName());
                strategies.forEach(strategy -> strategy.calculate(trk, result));

                result.setAnalyzeTime(System.nanoTime() - startTime);
                System.out.println(name + " " + result);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
