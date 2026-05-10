package org.example.model;


import java.time.LocalTime;

public class JourneyOption {
    private final String description;
    private final String firstTrainId;
    private final String secondTrainId;
    private final String departureStation;
    private final String arrivalStation;
    private final String changeoverStation;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;

    private JourneyOption(String description, String firstTrainId, String secondTrainId,
                          String departureStation, String arrivalStation, String changeoverStation,
                          LocalTime departureTime, LocalTime arrivalTime) {
        this.description = description;
        this.firstTrainId = firstTrainId;
        this.secondTrainId = secondTrainId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.changeoverStation = changeoverStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static JourneyOption direct(String trainId, String departureStation, String arrivalStation,
                                       LocalTime departureTime, LocalTime arrivalTime) {
        return new JourneyOption(
                "Direct train " + trainId,
                trainId,
                null,
                departureStation,
                arrivalStation,
                null,
                departureTime,
                arrivalTime
        );
    }

    public static JourneyOption withChangeover(String firstTrainId, String secondTrainId,
                                               String departureStation, String arrivalStation,
                                               String changeoverStation,
                                               LocalTime departureTime, LocalTime arrivalTime) {
        return new JourneyOption(
                "Train " + firstTrainId + " to " + changeoverStation + ", then train " + secondTrainId,
                firstTrainId,
                secondTrainId,
                departureStation,
                arrivalStation,
                changeoverStation,
                departureTime,
                arrivalTime
        );
    }

    public String getDescription() {
        return description;
    }

    public String getFirstTrainId() {
        return firstTrainId;
    }

    public String getSecondTrainId() {
        return secondTrainId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public String getChangeoverStation() {
        return changeoverStation;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return description + " | " + departureStation + " " + departureTime +
                " -> " + arrivalStation + " " + arrivalTime;
    }
}
