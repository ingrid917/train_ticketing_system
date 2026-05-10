package org.example.model;

import java.time.LocalTime;
import java.util.Objects;

public class StationStop {
    private String stationName;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public StationStop(String stationName, LocalTime arrivalTime, LocalTime departureTime) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getEffectiveTime() {
        return departureTime != null ? departureTime : arrivalTime;
    }

    @Override
    public String toString() {
        String arrival = arrivalTime == null ? "-" : arrivalTime.toString();
        String departure = departureTime == null ? "-" : departureTime.toString();
        return stationName + " (arr: " + arrival + ", dep: " + departure + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationStop)) return false;
        StationStop that = (StationStop) o;
        return Objects.equals(stationName, that.stationName)
                && Objects.equals(arrivalTime, that.arrivalTime)
                && Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, arrivalTime, departureTime);
    }
}

