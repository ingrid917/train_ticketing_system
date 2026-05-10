## Java Train Ticketing Application

This project is a Java Gradle console application that simulates a train ticketing system.
The application allows customers to search for train connections, book one or multiple tickets, and receive simulated email confirmations. It also provides administrator operations for managing train routes, trains, bookings, and train delays.


## Features:

# Customer Features
- View available trains
- Search for possible journeys between two stations
- Find direct journeys
- Find journeys that require one changeover
- Book one or multiple tickets on a train
- Prevent overbooking by checking the train capacity and already existing bookings
- Receive a simulated confirmation email after a successful booking

# Administrator Features
- Add new routes with stations and departure/arrival times
- Remove routes
- Modify existing route names
- Add new trains
- Remove trains
- Modify train capacity
- View bookings made for a specific train
- Report train delays
- Notify affected customers by simulated email when a delay is reported

---

## Technologies Used
- Java 17 or greater
- Gradle
- Console user interface


## Architecture
The project follows a layered architecture: model, repository, service, controller.

# Model Layer
The `model` package contains the domain classes used by the application.

# Repository Layer
The current implementation uses in-memory repositories based on Java collections.

# Service Layer
The `service` package contains the business logic of the application.

Examples of business logic:
- booking tickets
- checking available seats
- preventing overbooking
- searching direct journeys
- searching journeys with one changeover
- reporting train delays
- sending simulated email notifications

# Controller Layer
The `controller` package contains the console-based user interface.


## Routes

# Route R1 - Berlin to Prague
```
Berlin      departure: 08:00
Leipzig     arrival: 09:15, departure: 09:20
Dresden     arrival: 10:35, departure: 10:45
Prague      arrival: 13:00
```

# Route R2 - Munich to Frankfurt
```
Munich      departure: 07:30
Nuremberg   arrival: 08:45, departure: 08:55
Wurzburg    arrival: 10:00, departure: 10:05
Frankfurt   arrival: 11:20
```

# Route R3 - Frankfurt to Dusseldorf
```
Frankfurt   departure: 12:00
Cologne     arrival: 13:20, departure: 13:30
Dusseldorf  arrival: 14:10
```

# Route R4 - Hamburg to Berlin
```
Hamburg     departure: 09:00
Hannover    arrival: 10:20, departure: 10:30
Berlin      arrival: 12:00
```

# Route R5 - Dresden to Munich
```
Dresden     departure: 14:00
Leipzig     arrival: 15:10, departure: 15:20
Nuremberg   arrival: 17:30, departure: 17:40
Munich      arrival: 19:00
```

## Trains
```
T100 | ICE 100 | Route R1 | Capacity: 120
T200 | ICE 200 | Route R2 | Capacity: 100
T300 | RE 300  | Route R3 | Capacity: 80
T400 | IC 400  | Route R4 | Capacity: 90
T500 | ICE 500 | Route R5 | Capacity: 110
```


## How to Run the Application

# Requirements:
- JDK 17 or newer
- Gradle

# Run with Gradle
From the project root folder, run:
```bash
gradle run
```

# Alternative Manual Run
If Gradle is not available, the application can also be compiled and run manually:

```bash
javac -d out src/main/java/com/example/trainticketing/*.java src/main/java/com/example/trainticketing/*/*.java
java -cp out com.example.trainticketing.Main
```


## Main Menu
When the application starts, the following menu is displayed:

```text
=== Train Ticketing Application ===
1. Show trains
2. Find journey options
3. Book tickets
4. Administrator menu
0. Exit
```

---

## Example 1: Show Available Trains
# Input
```
1
```

# Output
```
Available trains:
T100 | ICE 100 | Route: R1 | Capacity: 120 | Delay: 0 minutes
T200 | ICE 200 | Route: R2 | Capacity: 100 | Delay: 0 minutes
T300 | RE 300 | Route: R3 | Capacity: 80 | Delay: 0 minutes
T400 | IC 400 | Route: R4 | Capacity: 90 | Delay: 0 minutes
T500 | ICE 500 | Route: R5 | Capacity: 110 | Delay: 0 minutes
```

---

## Example 2: Find a Direct Journey

# Input
```
2
Departure station: Berlin
Arrival station: Prague
```

# Output
```
Journey options:
Direct train T100 | Berlin 08:00 -> Prague 13:00
```

Explanation: Berlin and Prague are on the same route, so the application finds a direct connection.

---

## Example 3: Find a Journey with One Changeover

# Input
```
2
Departure station: Munich
Arrival station: Dusseldorf
```

# Output
```
Journey options:
Train T200 to Frankfurt, then train T300 | Munich 07:30 -> Dusseldorf 14:10
Changeover station: Frankfurt
```

Explanation: There is no direct train from Munich to Dusseldorf, but the passenger can travel from Munich to Frankfurt and then change trains from Frankfurt to Dusseldorf.

---

## Example 4: No Available Connection

# Input
```
2
Departure station: Prague
Arrival station: Hamburg
```

# Output
```
No possible connection found between these stations.
```

Explanation: The application shows an appropriate message when no direct or one-changeover connection is available.

---

## Example 5: Book Tickets Successfully

# Input
```
3
Train id: T100
Customer name: Anna Schmidt
Customer email: anna.schmidt@example.com
Departure station: Berlin
Arrival station: Prague
Number of tickets: 2
```

# Output
```
Booking created successfully.

--- Simulated Email ---
To: anna.schmidt@example.com
Subject: Train ticket booking confirmation

Dear Anna Schmidt,

Your booking has been confirmed.
Train: ICE 100
From: Berlin
To: Prague
Number of tickets: 2

Thank you for choosing our service.
-----------------------
```

Explanation: After a successful booking, the application stores the booking and sends a simulated confirmation email.

---

## Example 6: Prevent Overbooking

Assume train `T300` has only 80 seats available.

# Input
```
3
Train id: T300
Customer name: Max Weber
Customer email: max.weber@example.com
Departure station: Frankfurt
Arrival station: Dusseldorf
Number of tickets: 100
```

# Output
```
Booking failed: Not enough seats available. Available seats: 80
```

Explanation: The application checks the train capacity and existing bookings before creating a new booking. If the requested number of tickets is greater than the number of available seats, the booking is rejected.

---

## Administrator Menu

The administrator menu provides additional operations:

```text
=== Administrator Menu ===
1. Add route
2. Remove route
3. Modify route name
4. Add train
5. Remove train
6. Modify train capacity
7. Show bookings for train
8. Report train delay
9. Show routes
0. Back
```

---

## Example 7: Add a New Route

# Input
```
4
1
Route id: R6
Route name: Stuttgart to Munich
Number of stops: 3

Station name: Stuttgart
Arrival time: -
Departure time: 06:30

Station name: Ulm
Arrival time: 07:25
Departure time: 07:30

Station name: Munich
Arrival time: 08:45
Departure time: -
```

# Output
```
Route added successfully.
```

Explanation: The administrator can add a route with multiple stations and their arrival/departure times.

---

## Example 8: Add a New Train

# Input
```
4
4
Train id: T600
Train name: ICE 600
Route id: R6
Capacity: 100
```

# Output
```
Train added successfully.
```

Explanation: The administrator can add a new train and assign it to an existing route.

---

## Example 9: Modify Train Capacity

# Input
```
4
6
Train id: T600
New capacity: 130
```

# Output
```
Train capacity updated successfully.
```

Explanation: The administrator can modify the capacity of an existing train.

---

## Example 10: Show Bookings for a Train

# Input
```
4
7
Train id: T100
```

# Output
```
Bookings for train T100:
Booking ID: B1 | Customer: Anna Schmidt | Email: anna.schmidt@example.com | Tickets: 2 | Berlin -> Prague
```

If no bookings exist for that train, the application displays:

```text
No bookings found for this train.
```

---

## Example 11: Report Train Delay

# Input
```
4
8
Train id: T100
Delay in minutes: 25
```

# Output
```
Delay reported successfully.

--- Simulated Email ---
To: anna.schmidt@example.com
Subject: Train delay notification

Dear Anna Schmidt,

Train ICE 100 has a delay of 25 minutes.
Your journey: Berlin -> Prague

We apologize for the inconvenience.
-----------------------
```

Explanation: When a delay is reported, all customers who have bookings for that train are notified by simulated email.

---

## Error Handling
The application handles common invalid situations, for example:

- trying to book tickets on a train that does not exist
- trying to book more tickets than available
- searching for a connection that does not exist
- removing a route or train that does not exist
- showing bookings for a train without existing bookings
- entering invalid numeric values in the console

---

## Notes About Email Sending
The project does not send real emails through an external email server. Instead, the application uses an `EmailService` class that prints the email content in the console.


---

## Possible Future Improvements
Possible improvements include:

- storing data in a real database
- adding unit tests
- adding ticket prices
- adding seat numbers
- adding a graphical user interface
- adding a REST API with Spring Boot
- sending real emails through SMTP
- adding user authentication for administrator operations

---

## Summary

This project demonstrates a complete Java console application for train ticketing. It includes predefined train schedules, route search, ticket booking, overbooking prevention, administrator operations, delay reporting, and simulated email notifications.