package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;
import at.kaindorf.hiking.data.Trk;

public interface Strategy {
    void calculate (Trk trk, Result result);
}
