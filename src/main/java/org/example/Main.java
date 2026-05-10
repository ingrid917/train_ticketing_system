package org.example;

import org.example.controller.ConsoleController;
import org.example.repository.BookingRepository;
import org.example.repository.RouteRepository;
import org.example.repository.TrainRepository;
import org.example.service.DataInitializer;
import org.example.service.EmailService;
import org.example.service.TicketingService;

public class Main {
    public static void main(String[] args) {
        TrainRepository trainRepository = new TrainRepository();
        RouteRepository routeRepository = new RouteRepository();
        BookingRepository bookingRepository = new BookingRepository();
        EmailService emailService = new EmailService();

        DataInitializer dataInitializer = new DataInitializer(trainRepository, routeRepository);
        dataInitializer.loadSampleData();

        TicketingService ticketingService = new TicketingService(
                trainRepository,
                routeRepository,
                bookingRepository,
                emailService
        );

        ConsoleController consoleController = new ConsoleController(ticketingService);
        consoleController.start();
    }
}
