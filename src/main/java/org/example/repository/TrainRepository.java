package org.example.repository;
import org.example.model.Train;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TrainRepository {
    private final List<Train> trains = new ArrayList<>();

    public List<Train> findAll() {
        return Collections.unmodifiableList(trains);
    }

    public Optional<Train> findById(String id) {
        return trains.stream()
                .filter(train -> train.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void save(Train train) {
        if (findById(train.getId()).isPresent()) {
            throw new IllegalArgumentException("A train with this id already exists.");
        }
        trains.add(train);
    }

    public boolean deleteById(String id) {
        return trains.removeIf(train -> train.getId().equalsIgnoreCase(id));
    }
}
