package org.example.service;


import org.example.model.Booking;

public class EmailService {
    public void sendBookingConfirmation(Booking booking) {
        System.out.println("\n--- EMAIL SENT ---");
        System.out.println("To: " + booking.getCustomerEmail());
        System.out.println("Subject: Booking confirmation");
        System.out.println("Hello " + booking.getCustomerName() + ", your booking " + booking.getId()
                + " for train " + booking.getTrainId() + " has been confirmed.");
        System.out.println("Tickets: " + booking.getNumberOfTickets());
        System.out.println("Route: " + booking.getDepartureStation() + " -> " + booking.getArrivalStation());
        System.out.println("------------------\n");
    }

    public void sendDelayNotification(String email, String customerName, String trainId, int delayMinutes) {
        System.out.println("\n--- EMAIL SENT ---");
        System.out.println("To: " + email);
        System.out.println("Subject: Train delay notification");
        System.out.println("Hello " + customerName + ", train " + trainId
                + " has a delay of " + delayMinutes + " minutes.");
        System.out.println("------------------\n");
    }
}
