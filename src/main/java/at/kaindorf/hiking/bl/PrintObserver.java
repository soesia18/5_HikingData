package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;

public class PrintObserver implements Observer {
    @Override
    public void update(Result result) {
        System.out.println(result);
    }
}
