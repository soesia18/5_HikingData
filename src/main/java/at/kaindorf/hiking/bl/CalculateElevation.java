package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;
import at.kaindorf.hiking.data.Trk;

public class CalculateElevation implements Strategy {
    @Override
    public void calculate(Trk trk, Result result) {
        float elePos = 0;
        float eleNeg = 0;

        for (int i = 0; i < trk.getTrackingPoints().size() - 1; i++) {
            double elevation = (trk.getTrackingPoints().get(i).getElevation() - trk.getTrackingPoints().get(i + 1).getElevation());
            if (elevation > 0) {
                elePos += elevation;
            } else {
                eleNeg += elevation;
            }
        }

        result.setElePos(elePos);
        result.setEleNeg(eleNeg);
    }
}
