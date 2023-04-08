package at.kaindorf.hiking.data;

import lombok.*;

@Setter
@Getter
@ToString
public class Result {
    private String name;
    private float distance;
    private float elePos;
    private float eleNeg;
    private long analyzeTime;

    public Result(String name) {
        this.name = name;
    }
}
