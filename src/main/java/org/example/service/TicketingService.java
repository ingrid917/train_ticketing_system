package org.example.service;


import org.example.model.Booking;
import org.example.model.Route;
import org.example.model.StationStop;
import org.example.model.Train;
import org.example.repository.BookingRepository;
import org.example.repository.RouteRepository;
import org.example.repository.TrainRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class TicketingService {
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public TicketingService(TrainRepository trainRepository,
                            RouteRepository routeRepository,
                            BookingRepository bookingRepository,
                            EmailService emailService) {
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Booking> getBookingsForTrain(String trainId) {
        ensureTrainExists(trainId);
        return bookingRepository.findByTrainId(trainId);
    }

    public Booking bookTickets(String trainId,
                               String customerName,
                               String customerEmail,
                               String departureStation,
                               String arrivalStation,
                               int numberOfTickets) {
        if (numberOfTickets <= 0) {
            throw new IllegalArgumentException("Number of tickets must be positive.");
        }

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalArgumentException("Train not found."));
        Route route = routeRepository.findById(train.getRouteId())
                .orElseThrow(() -> new IllegalStateException("Route for train not found."));

        if (!route.canTravelForward(departureStation, arrivalStation)) {
            throw new IllegalArgumentException("The selected train does not travel from "
                    + departureStation + " to " + arrivalStation + " in this order.");
        }

        int bookedTickets = bookingRepository.countTicketsByTrainId(trainId);
        int availableSeats = train.getCapacity() - bookedTickets;

        if (numberOfTickets > availableSeats) {
            throw new IllegalArgumentException("Not enough seats available. Available seats: " + availableSeats);
        }

        Booking booking = new Booking(
                generateBookingId(),
                trainId,
                customerName,
                customerEmail,
                departureStation,
                arrivalStation,
                numberOfTickets
        );

        bookingRepository.save(booking);
        emailService.sendBookingConfirmation(booking);
        return booking;
    }

    public void addRoute(Route route) {
        validateRoute(route);
        routeRepository.save(route);
    }

    public boolean removeRoute(String routeId) {
        boolean routeInUse = trainRepository.findAll().stream()
                .anyMatch(train -> train.getRouteId().equalsIgnoreCase(routeId));
        if (routeInUse) {
            throw new IllegalArgumentException("Cannot remove route because at least one train uses it.");
        }
        return routeRepository.deleteById(routeId);
    }

    public void modifyRouteName(String routeId, String newName) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));
        route.setName(newName);
    }

    public void modifyRouteStops(String routeId, List<StationStop> newStops) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found."));
        validateStops(newStops);
        route.replaceStops(newStops);
    }

    public void addTrain(Train train) {
        if (train.getCapacity() <= 0) {
            throw new IllegalArgumentException("Train capacity must be positive.");
        }
        routeRepository.findById(train.getRouteId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot add train because route does not exist."));
        trainRepository.save(train);
    }

    public boolean removeTrain(String trainId) {
        if (!bookingRepository.findByTrainId(trainId).isEmpty()) {
            throw new IllegalArgumentException("Cannot remove train because it has bookings.");
        }
        return trainRepository.deleteById(trainId);
    }

    public void modifyTrainCapacity(String trainId, int newCapacity) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalArgumentException("Train not found."));
        int bookedTickets = bookingRepository.countTicketsByTrainId(trainId);
        if (newCapacity < bookedTickets) {
            throw new IllegalArgumentException("New capacity cannot be lower than already booked tickets: " + bookedTickets);
        }
        train.setCapacity(newCapacity);
    }

    public void reportTrainDelay(String trainId, int delayMinutes) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalArgumentException("Train not found."));
        train.setDelayMinutes(delayMinutes);

        List<Booking> bookings = bookingRepository.findByTrainId(trainId);
        for (Booking booking : bookings) {
            emailService.sendDelayNotification(
                    booking.getCustomerEmail(),
                    booking.getCustomerName(),
                    trainId,
                    delayMinutes
            );
        }
    }

    private Route getRouteForTrain(Train train) {
        return routeRepository.findById(train.getRouteId())
                .orElseThrow(() -> new IllegalStateException("Route not found for train " + train.getId()));
    }

    private void ensureTrainExists(String trainId) {
        trainRepository.findById(trainId)
                .orElseThrow(() -> new IllegalArgumentException("Train not found."));
    }

    private void validateRoute(Route route) {
        if (route.getId() == null || route.getId().isBlank()) {
            throw new IllegalArgumentException("Route id is required.");
        }
        if (route.getName() == null || route.getName().isBlank()) {
            throw new IllegalArgumentException("Route name is required.");
        }
        validateStops(route.getStops());
    }

    private void validateStops(List<StationStop> stops) {
        if (stops == null || stops.size() < 2) {
            throw new IllegalArgumentException("A route must have at least two stations.");
        }
    }

    private String generateBookingId() {
        return "B-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
