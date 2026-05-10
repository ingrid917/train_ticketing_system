package org.example.model;

import org.example.model.StationStop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Route {
private String id;
private String name;
private final List<StationStop> stops;

public Route(String id, String name, List<StationStop> stops) {
    this.id = id;
    this.name = name;
    this.stops = new ArrayList<>(stops);
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<StationStop> getStops() {
    return Collections.unmodifiableList(stops);
}

public boolean containsStation(String stationName) {
    return getStopIndex(stationName) >= 0;
}

public int getStopIndex(String stationName) {
    for (int i = 0; i < stops.size(); i++) {
        if (stops.get(i).getStationName().equalsIgnoreCase(stationName)) {
            return i;
        }
    }
    return -1;
}

public StationStop getStop(String stationName) {
    int index = getStopIndex(stationName);
    return index >= 0 ? stops.get(index) : null;
}

public boolean canTravelForward(String departureStation, String arrivalStation) {
    int departureIndex = getStopIndex(departureStation);
    int arrivalIndex = getStopIndex(arrivalStation);
    return departureIndex >= 0 && arrivalIndex >= 0 && departureIndex < arrivalIndex;
}

public void replaceStops(List<StationStop> newStops) {
    stops.clear();
    stops.addAll(newStops);
}

@Override
public String toString() {
    return "Route{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", stops=" + stops +
            '}';
}
}
