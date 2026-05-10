package org.example.service;

import org.example.model.Route;
import org.example.model.StationStop;
import org.example.model.Train;
import org.example.repository.RouteRepository;
import org.example.repository.TrainRepository;

import java.time.LocalTime;
import java.util.List;
public class DataInitializer {
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    public DataInitializer(TrainRepository trainRepository, RouteRepository routeRepository) {
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    public void loadSampleData() {
        Route route1 = new Route("R1", "Berlin to Prague", List.of(
                new StationStop("Berlin", null, LocalTime.of(8, 0)),
                new StationStop("Leipzig", LocalTime.of(9, 15), LocalTime.of(9, 20)),
                new StationStop("Dresden", LocalTime.of(10, 35), LocalTime.of(10, 45)),
                new StationStop("Prague", LocalTime.of(13, 0), null)
        ));

        Route route2 = new Route("R2", "Munich to Frankfurt", List.of(
                new StationStop("Munich", null, LocalTime.of(7, 30)),
                new StationStop("Nuremberg", LocalTime.of(8, 45), LocalTime.of(8, 55)),
                new StationStop("Wurzburg", LocalTime.of(10, 0), LocalTime.of(10, 5)),
                new StationStop("Frankfurt", LocalTime.of(11, 20), null)
        ));

        Route route3 = new Route("R3", "Frankfurt to Dusseldorf", List.of(
                new StationStop("Frankfurt", null, LocalTime.of(12, 0)),
                new StationStop("Cologne", LocalTime.of(13, 20), LocalTime.of(13, 30)),
                new StationStop("Dusseldorf", LocalTime.of(14, 10), null)
        ));

        Route route4 = new Route("R4", "Hamburg to Berlin", List.of(
                new StationStop("Hamburg", null, LocalTime.of(9, 0)),
                new StationStop("Hannover", LocalTime.of(10, 20), LocalTime.of(10, 30)),
                new StationStop("Berlin", LocalTime.of(12, 0), null)
        ));

        Route route5 = new Route("R5", "Dresden to Munich", List.of(
                new StationStop("Dresden", null, LocalTime.of(14, 0)),
                new StationStop("Leipzig", LocalTime.of(15, 10), LocalTime.of(15, 20)),
                new StationStop("Nuremberg", LocalTime.of(17, 30), LocalTime.of(17, 40)),
                new StationStop("Munich", LocalTime.of(19, 0), null)
        ));

        routeRepository.save(route1);
        routeRepository.save(route2);
        routeRepository.save(route3);
        routeRepository.save(route4);
        routeRepository.save(route5);

        trainRepository.save(new Train("T100", "ICE 100", "R1", 120));
        trainRepository.save(new Train("T200", "ICE 200", "R2", 100));
        trainRepository.save(new Train("T300", "RE 300", "R3", 80));
        trainRepository.save(new Train("T400", "IC 400", "R4", 90));
        trainRepository.save(new Train("T500", "ICE 500", "R5", 110));
    }
}