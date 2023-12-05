package uk.gov.dwp.codetest.util;

public class DistanceCalculator {

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    private static final double kilometerConversion = 1.609344;
    public static boolean withinGivenMilesOfLondon(int miles, double locationLatitude, double locationLongitude) {
        final double londonLatitude = 51.50722;
        final double londonLongitude = -0.1275;
        double range = miles * kilometerConversion; //convert miles to km
        return calculateDistance(londonLatitude, londonLongitude, locationLatitude, locationLongitude) <= range; //ToDo investigate rounding error
    }
    public static double calculateDistance(double startLat, double startLong,
                                           double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
