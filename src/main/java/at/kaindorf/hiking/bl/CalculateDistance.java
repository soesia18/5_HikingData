package at.kaindorf.hiking.bl;

import at.kaindorf.hiking.data.Result;
import at.kaindorf.hiking.data.Trk;

public class CalculateDistance implements Strategy {
    @Override
    public void calculate(Trk trk, Result result) {

        float distance = 0;

        for (int i = 0; i < trk.getTrackingPoints().size() - 1; i++) {
            double radius = 6371e3; // metres
            double phi1 = Math.toRadians(trk.getTrackingPoints().get(i).getLat());
            double phi2 = Math.toRadians(trk.getTrackingPoints().get(i + 1).getLat());

            double dPhi = Math.toRadians(trk.getTrackingPoints().get(i + 1).getLat() - trk.getTrackingPoints().get(i).getLat());
            double dLambda = Math.toRadians(trk.getTrackingPoints().get(i + 1).getLon() - trk.getTrackingPoints().get(i).getLon());

            double a = Math.pow(Math.sin(dPhi/2),2) + Math.cos(phi1) * Math.cos(phi2) + Math.pow(Math.sin(dLambda/2),2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            distance += radius * c;
        }

        result.setDistance(distance);
    }
}
