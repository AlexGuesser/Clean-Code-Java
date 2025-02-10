package dev.alexguesser.ride.domain.vo;

public record Coord(
        double latitude,
        double longitude
) {

    public Coord {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public static Coord create(double latitude, double longitude) {
        return new Coord(latitude, longitude);
    }

}
