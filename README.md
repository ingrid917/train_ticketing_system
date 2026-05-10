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


## Input and Output Examples for All Functionalities
This section presents examples of input and output for all supported functionalities of the application.

## Customer Functionality: Show Available Trains

### Input

```text
1
```

### Output

```text
Available trains:
T100 | ICE 100 | Route: R1 | Capacity: 120 | Delay: 0 minutes
T200 | ICE 200 | Route: R2 | Capacity: 100 | Delay: 0 minutes
T300 | RE 300 | Route: R3 | Capacity: 80 | Delay: 0 minutes
T400 | IC 400 | Route: R4 | Capacity: 90 | Delay: 0 minutes
T500 | ICE 500 | Route: R5 | Capacity: 110 | Delay: 0 minutes
```

Explanation: The user can see all predefined trains, their route ids, capacities, and current delays.

---

## Customer Functionality: Find a Direct Journey Between Two Stations

### Input

```text
2
Departure station: Berlin
Arrival station: Prague
```

### Output

```text
Journey options:
Direct train T100 | Berlin 08:00 -> Prague 13:00
```

Explanation: The application finds a direct connection because Berlin and Prague are part of the same route.

---

## Customer Functionality: Find a Journey with One Changeover

### Input

```text
2
Departure station: Munich
Arrival station: Dusseldorf
```

### Output

```text
Journey options:
Train T200 to Frankfurt, then train T300 | Munich 07:30 -> Dusseldorf 14:10
Changeover station: Frankfurt
```

Explanation: There is no direct train from Munich to Dusseldorf, but the passenger can travel from Munich to Frankfurt and then change trains from Frankfurt to Dusseldorf.

---

## Customer Functionality: Show an Error Message When No Connection Exists

### Input

```text
2
Departure station: Prague
Arrival station: Hamburg
```

### Output

```text
No possible connection found between these stations.
```

Explanation: The application displays an appropriate message when no direct or one-changeover connection exists between the two stations.

---

## Customer Functionality: Book One Ticket

### Input

```text
3
Train id: T100
Customer name: Anna Schmidt
Customer email: anna.schmidt@example.com
Departure station: Berlin
Arrival station: Prague
Number of tickets: 1
```

### Output

```text
Booking created successfully.

--- Simulated Email ---
To: anna.schmidt@example.com
Subject: Train ticket booking confirmation

Dear Anna Schmidt,

Your booking has been confirmed.
Train: ICE 100
From: Berlin
To: Prague
Number of tickets: 1

Thank you for choosing our service.
-----------------------
```

Explanation: The application creates the booking and sends a simulated confirmation email to the customer.

---

## Customer Functionality: Book Multiple Tickets

### Input

```text
3
Train id: T200
Customer name: Max Weber
Customer email: max.weber@example.com
Departure station: Munich
Arrival station: Frankfurt
Number of tickets: 3
```

### Output

```text
Booking created successfully.

--- Simulated Email ---
To: max.weber@example.com
Subject: Train ticket booking confirmation

Dear Max Weber,

Your booking has been confirmed.
Train: ICE 200
From: Munich
To: Frankfurt
Number of tickets: 3

Thank you for choosing our service.
-----------------------
```

Explanation: The application supports booking more than one ticket in a single operation.

---

## Customer Functionality: Prevent Overbooking

Assume train `T300` has a capacity of 80 seats.

### Input

```text
3
Train id: T300
Customer name: Lisa Muller
Customer email: lisa.muller@example.com
Departure station: Frankfurt
Arrival station: Dusseldorf
Number of tickets: 100
```

### Output

```text
Booking failed: Not enough seats available. Available seats: 80
```

Explanation: The application checks the train capacity and the number of already booked tickets before creating a new booking. If the requested number of tickets is greater than the available seats, the booking is rejected.

---

## Customer Functionality: Booking Fails When the Train Does Not Exist

### Input

```text
3
Train id: T999
Customer name: Jonas Fischer
Customer email: jonas.fischer@example.com
Departure station: Berlin
Arrival station: Prague
Number of tickets: 2
```

### Output

```text
Booking failed: Train not found.
```

Explanation: The application does not allow bookings for trains that do not exist.

---

## Administrator Functionality: Open Administrator Menu

### Input

```text
4
```

### Output

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

Explanation: The administrator menu contains the additional operations required for managing routes, trains, bookings, and delays.

---

## Administrator Functionality: Add a New Route with Stations

### Input

```text
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

### Output

```text
Route added successfully.
```

Explanation: The administrator can add a new route consisting of multiple stations. The first station has no arrival time, and the last station has no departure time.

---

## Administrator Functionality: Remove an Existing Route

### Input

```text
4
2
Route id: R6
```

### Output

```text
Route removed successfully.
```

Explanation: The administrator can remove an existing route by entering its route id.

---

## Administrator Functionality: Try to Remove a Route That Does Not Exist

### Input

```text
4
2
Route id: R99
```

### Output

```text
Route not found.
```

Explanation: If the entered route id does not exist, the application displays an appropriate error message.

---

## Administrator Functionality: Modify Route Name

### Input

```text
4
3
Route id: R1
New route name: Berlin to Prague Express
```

### Output

```text
Route name updated successfully.
```

Explanation: The administrator can modify the name of an existing route. The route id, stations, and times remain unchanged.

---

## Administrator Functionality: Add a New Train

Before using this example, route `R6` should already exist.

### Input

```text
4
4
Train id: T600
Train name: ICE 600
Route id: R6
Capacity: 100
```

### Output

```text
Train added successfully.
```

Explanation: The administrator can add a new train and assign it to an existing route.

---

## Administrator Functionality: Add a Train with a Non-Existing Route

### Input

```text
4
4
Train id: T700
Train name: ICE 700
Route id: R99
Capacity: 100
```

### Output

```text
Train could not be added: Route not found.
```

Explanation: A train must be assigned to an existing route. The application prevents adding trains with invalid route ids.

---

## Administrator Functionality: Remove an Existing Train

### Input

```text
4
5
Train id: T600
```

### Output

```text
Train removed successfully.
```

Explanation: The administrator can remove an existing train by entering its train id.

---

## Administrator Functionality: Try to Remove a Train That Does Not Exist

### Input

```text
4
5
Train id: T999
```

### Output

```text
Train not found.
```

Explanation: If the entered train id does not exist, the application displays an appropriate error message.

---

## Administrator Functionality: Modify Train Capacity

### Input

```text
4
6
Train id: T100
New capacity: 150
```

### Output

```text
Train capacity updated successfully.
```

Explanation: The administrator can modify the capacity of an existing train.

In this implementation, modifying a train means updating its capacity. The train id, train name, and assigned route remain unchanged.

---

## Administrator Functionality: Try to Modify the Capacity of a Non-Existing Train

### Input

```text
4
6
Train id: T999
New capacity: 150
```

### Output

```text
Train not found.
```

Explanation: The application displays an error message if the administrator tries to modify a train that does not exist.

---

## Administrator Functionality: Show Bookings for a Train

Before using this functionality, assume the following booking was made:

```text
Train id: T100
Customer name: Anna Schmidt
Customer email: anna.schmidt@example.com
Departure station: Berlin
Arrival station: Prague
Number of tickets: 2
```

### Input

```text
4
7
Train id: T100
```

### Output

```text
Bookings for train T100:
Booking ID: B1 | Customer: Anna Schmidt | Email: anna.schmidt@example.com | Tickets: 2 | Berlin -> Prague
```

Explanation: The administrator can see the bookings made for a specific train.

---

## Administrator Functionality: Show Bookings for a Train with No Bookings

### Input

```text
4
7
Train id: T500
```

### Output

```text
No bookings found for this train.
```

Explanation: If there are no bookings for the selected train, the application displays an appropriate message.

---

## Administrator Functionality: Report a Train Delay

Before using this functionality, assume there is at least one booking for train `T100`.

### Input

```text
4
8
Train id: T100
Delay in minutes: 25
```

### Output

```text
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

Explanation: The administrator can specify that a train has encountered a delay. All customers with bookings on that train are notified through simulated email messages.

---

## Administrator Functionality: Report a Delay for a Train with Multiple Bookings

Before using this functionality, assume train `T200` has two bookings:

```text
Booking 1:
Customer name: Max Weber
Customer email: max.weber@example.com
Tickets: 3
Munich -> Frankfurt

Booking 2:
Customer name: Clara Hoffmann
Customer email: clara.hoffmann@example.com
Tickets: 1
Munich -> Frankfurt
```

### Input

```text
4
8
Train id: T200
Delay in minutes: 15
```

### Output

```text
Delay reported successfully.

--- Simulated Email ---
To: max.weber@example.com
Subject: Train delay notification

Dear Max Weber,

Train ICE 200 has a delay of 15 minutes.
Your journey: Munich -> Frankfurt

We apologize for the inconvenience.
-----------------------

--- Simulated Email ---
To: clara.hoffmann@example.com
Subject: Train delay notification

Dear Clara Hoffmann,

Train ICE 200 has a delay of 15 minutes.
Your journey: Munich -> Frankfurt

We apologize for the inconvenience.
-----------------------
```

Explanation: When a train is delayed, every customer who has a booking for that train receives a simulated delay notification.

---

## Administrator Functionality: Report a Delay for a Non-Existing Train

### Input

```text
4
8
Train id: T999
Delay in minutes: 20
```

### Output

```text
Train not found.
```

Explanation: The application displays an error message if the administrator tries to report a delay for a train that does not exist.

---

## Administrator Functionality: Show All Routes

### Input

```text
4
9
```

### Output

```text
Available routes:

Route R1 | Berlin to Prague
Berlin departure: 08:00
Leipzig arrival: 09:15, departure: 09:20
Dresden arrival: 10:35, departure: 10:45
Prague arrival: 13:00

Route R2 | Munich to Frankfurt
Munich departure: 07:30
Nuremberg arrival: 08:45, departure: 08:55
Wurzburg arrival: 10:00, departure: 10:05
Frankfurt arrival: 11:20

Route R3 | Frankfurt to Dusseldorf
Frankfurt departure: 12:00
Cologne arrival: 13:20, departure: 13:30
Dusseldorf arrival: 14:10

Route R4 | Hamburg to Berlin
Hamburg departure: 09:00
Hannover arrival: 10:20, departure: 10:30
Berlin arrival: 12:00

Route R5 | Dresden to Munich
Dresden departure: 14:00
Leipzig arrival: 15:10, departure: 15:20
Nuremberg arrival: 17:30, departure: 17:40
Munich arrival: 19:00
```

Explanation: The administrator can display all existing routes together with their stations and arrival/departure times.

---

## Administrator Functionality: Return from Administrator Menu to Main Menu

### Input

```text
4
0
```

### Output

```text
=== Train Ticketing Application ===
1. Show trains
2. Find journey options
3. Book tickets
4. Administrator menu
0. Exit
```

Explanation: The administrator can return to the main menu.

---

## General Functionality: Exit the Application

### Input

```text
0
```

### Output

```text
Goodbye!
```

Explanation: The user can exit the application from the main menu.

---

## Notes About the Examples

The exact wording of the output may differ slightly depending on the implementation, but the behavior should remain the same.

The application supports all mandatory functionalities required by the assignment:

- booking one or multiple tickets
- preventing overbooking
- sending simulated booking confirmation emails
- finding direct journeys between two stations
- finding journeys with one changeover
- showing an error message when no connection exists
- adding, removing, and modifying routes
- adding, removing, and modifying trains
- showing bookings for a train
- reporting train delays
- notifying affected customers by simulated email
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