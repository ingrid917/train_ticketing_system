package org.example.controller;

import org.example.model.*;
import org.example.service.TicketingService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final TicketingService ticketingService;
    private final Scanner scanner;

    public ConsoleController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int option = readInt("Choose an option: ");

            try {
                switch (option) {
                    case 1 -> showTrains();
                    case 2 -> findJourneyOptions();
                    case 3 -> bookTickets();
                    case 4 -> showAdminMenu();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Train Ticketing Application ===");
        System.out.println("1. Show trains");
        System.out.println("2. Find journey options");
        System.out.println("3. Book tickets");
        System.out.println("4. Administrator menu");
        System.out.println("0. Exit");
    }

    private void showAdminMenu() {
        boolean inAdminMenu = true;

        while (inAdminMenu) {
            printAdminMenu();
            int option = readInt("Choose an admin option: ");

            try {
                switch (option) {
                    case 1 -> addRoute();
                    case 2 -> removeRoute();
                    case 3 -> modifyRouteName();
                    case 4 -> modifyRouteStops();
                    case 5 -> addTrain();
                    case 6 -> removeTrain();
                    case 7 -> modifyTrainCapacity();
                    case 8 -> showBookingsForTrain();
                    case 9 -> reportTrainDelay();
                    case 10 -> showRoutes();
                    case 0 -> inAdminMenu = false;
                    default -> System.out.println("Invalid admin option.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private void printAdminMenu() {
        System.out.println("\n=== Administrator Menu ===");
        System.out.println("1. Add route");
        System.out.println("2. Remove route");
        System.out.println("3. Modify route name");
        System.out.println("4. Modify route stops");
        System.out.println("5. Add train");
        System.out.println("6. Remove train");
        System.out.println("7. Modify train capacity");
        System.out.println("8. Show bookings for train");
        System.out.println("9. Report train delay");
        System.out.println("10. Show routes");
        System.out.println("0. Back");
    }

    private void showTrains() {
        System.out.println("\nAvailable trains:");
        for (Train train : ticketingService.getAllTrains()) {
            System.out.println(train);
        }
    }

    private void showRoutes() {
        System.out.println("\nAvailable routes:");
        for (Route route : ticketingService.getAllRoutes()) {
            System.out.println(route);
        }
    }

    private void findJourneyOptions() {
        String departureStation = readString("Departure station: ");
        String arrivalStation = readString("Arrival station: ");

        List<JourneyOption> options = ticketingService.findJourneyOptions(departureStation, arrivalStation);

        if (options.isEmpty()) {
            System.out.println("No possible connection found between these stations.");
            return;
        }

        System.out.println("\nJourney options:");
        for (JourneyOption option : options) {
            System.out.println(option);
        }
    }

    private void bookTickets() {
        String trainId = readString("Train id: ");
        String customerName = readString("Customer name: ");
        String customerEmail = readString("Customer email: ");
        String departureStation = readString("Departure station: ");
        String arrivalStation = readString("Arrival station: ");
        int tickets = readInt("Number of tickets: ");

        Booking booking = ticketingService.bookTickets(
                trainId,
                customerName,
                customerEmail,
                departureStation,
                arrivalStation,
                tickets
        );

        System.out.println("Booking created successfully: " + booking);
    }

    private void addRoute() {
        String routeId = readString("New route id: ");
        String routeName = readString("Route name: ");
        int numberOfStops = readInt("Number of station stops: ");

        List<StationStop> stops = readStops(numberOfStops);
        ticketingService.addRoute(new Route(routeId, routeName, stops));
        System.out.println("Route added successfully.");
    }

    private void removeRoute() {
        String routeId = readString("Route id to remove: ");
        boolean removed = ticketingService.removeRoute(routeId);
        System.out.println(removed ? "Route removed successfully." : "Route not found.");
    }

    private void modifyRouteName() {
        String routeId = readString("Route id: ");
        String newName = readString("New route name: ");
        ticketingService.modifyRouteName(routeId, newName);
        System.out.println("Route name updated successfully.");
    }

    private void modifyRouteStops() {
        String routeId = readString("Route id: ");
        int numberOfStops = readInt("New number of station stops: ");
        List<StationStop> stops = readStops(numberOfStops);
        ticketingService.modifyRouteStops(routeId, stops);
        System.out.println("Route stops updated successfully.");
    }

    private List<StationStop> readStops(int numberOfStops) {
        List<StationStop> stops = new ArrayList<>();
        for (int i = 0; i < numberOfStops; i++) {
            System.out.println("Station stop " + (i + 1) + ":");
            String stationName = readString("Station name: ");
            LocalTime arrivalTime = readOptionalTime("Arrival time HH:mm, or '-' for none: ");
            LocalTime departureTime = readOptionalTime("Departure time HH:mm, or '-' for none: ");
            stops.add(new StationStop(stationName, arrivalTime, departureTime));
        }
        return stops;
    }

    private void addTrain() {
        String trainId = readString("New train id: ");
        String trainName = readString("Train name: ");
        String routeId = readString("Route id: ");
        int capacity = readInt("Capacity: ");

        ticketingService.addTrain(new Train(trainId, trainName, routeId, capacity));
        System.out.println("Train added successfully.");
    }

    private void removeTrain() {
        String trainId = readString("Train id to remove: ");
        boolean removed = ticketingService.removeTrain(trainId);
        System.out.println(removed ? "Train removed successfully." : "Train not found.");
    }

    private void modifyTrainCapacity() {
        String trainId = readString("Train id: ");
        int newCapacity = readInt("New capacity: ");
        ticketingService.modifyTrainCapacity(trainId, newCapacity);
        System.out.println("Train capacity updated successfully.");
    }

    private void showBookingsForTrain() {
        String trainId = readString("Train id: ");
        List<Booking> bookings = ticketingService.getBookingsForTrain(trainId);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for this train.");
            return;
        }

        System.out.println("\nBookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void reportTrainDelay() {
        String trainId = readString("Train id: ");
        int delayMinutes = readInt("Delay in minutes: ");
        ticketingService.reportTrainDelay(trainId, delayMinutes);
        System.out.println("Delay reported successfully. Customers were notified if bookings exist.");
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private LocalTime readOptionalTime(String message) {
        while (true) {
            String input = readString(message);
            if (input.equals("-")) {
                return null;
            }
            try {
                return LocalTime.parse(input);
            } catch (Exception exception) {
                System.out.println("Invalid time format. Use HH:mm or '-'.");
            }
        }
    }
}

