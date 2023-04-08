package at.kaindorf.hiking.thread;

import at.kaindorf.hiking.bl.Strategy;
import at.kaindorf.hiking.bl.Subject;
import at.kaindorf.hiking.data.Queue;
import at.kaindorf.hiking.data.Result;
import at.kaindorf.hiking.data.Trk;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TrkConsumer extends Subject implements Runnable {

    private final String name;
    private Queue queue;

    private List<Strategy> strategies;

    @Override
    public void run() {
        while (!(queue.isEmpty() && queue.isFinished())) {
            try {
                long startTime = System.nanoTime();
                Trk trk = queue.dequeue();
                Result result = new Result(trk.getName());
                strategies.forEach(strategy -> strategy.calculate(trk, result));

                result.setAnalyzeTime(System.nanoTime() - startTime);
                //System.out.println(name + " " + result);
                super.notifyObservers(result);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread " + name + " interrupted");
            }
        }
        System.out.println("Thread " + name + " finished");
        Thread.getAllStackTraces().keySet().forEach(Thread::interrupt);
    }
}
