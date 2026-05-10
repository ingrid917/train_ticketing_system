package org.example.repository;


import org.example.model.Booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();

    public List<Booking> findAll() {
        return Collections.unmodifiableList(bookings);
    }

    public List<Booking> findByTrainId(String trainId) {
        return bookings.stream()
                .filter(booking -> booking.getTrainId().equalsIgnoreCase(trainId))
                .collect(Collectors.toList());
    }

    public void save(Booking booking) {
        bookings.add(booking);
    }

    public int countTicketsByTrainId(String trainId) {
        return bookings.stream()
                .filter(booking -> booking.getTrainId().equalsIgnoreCase(trainId))
                .mapToInt(Booking::getNumberOfTickets)
                .sum();
    }
}

