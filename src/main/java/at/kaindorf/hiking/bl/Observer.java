package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;

public interface Observer {
    void update(Result result);
}
