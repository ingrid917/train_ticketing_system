package org.example.repository;

import org.example.model.StationStop;
import org.example.model.Route;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RouteRepository {
    private final List<Route> routes = new ArrayList<>();

    public List<Route> findAll() {
        return Collections.unmodifiableList(routes);
    }

    public Optional<Route> findById(String id) {
        return routes.stream()
                .filter(route -> route.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void save(Route route) {
        if (findById(route.getId()).isPresent()) {
            throw new IllegalArgumentException("A route with this id already exists.");
        }
        routes.add(route);
    }

    public boolean deleteById(String id) {
        return routes.removeIf(route -> route.getId().equalsIgnoreCase(id));
    }
}
