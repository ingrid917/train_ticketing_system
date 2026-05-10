package org.example.model;

import java.time.LocalDateTime;

public class Booking {
    private final String id;
    private final String trainId;
    private final String customerName;
    private final String customerEmail;
    private final String departureStation;
    private final String arrivalStation;
    private final int numberOfTickets;
    private final LocalDateTime createdAt;

    public Booking(String id, String trainId, String customerName, String customerEmail,
                   String departureStation, String arrivalStation, int numberOfTickets) {
        this.id = id;
        this.trainId = trainId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.numberOfTickets = numberOfTickets;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", trainId='" + trainId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", from='" + departureStation + '\'' +
                ", to='" + arrivalStation + '\'' +
                ", tickets=" + numberOfTickets +
                ", createdAt=" + createdAt +
                '}';
    }
}
