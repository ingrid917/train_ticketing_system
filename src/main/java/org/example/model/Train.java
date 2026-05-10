package org.example.model;

public class Train {
    private String id;
    private String name;
    private String routeId;
    private int capacity;
    private int delayMinutes;

    public Train(String id, String name, String routeId, int capacity) {
        this.id = id;
        this.name = name;
        this.routeId = routeId;
        this.capacity = capacity;
        this.delayMinutes = 0;
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
        this.capacity = capacity;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay cannot be negative.");
        }
        this.delayMinutes = delayMinutes;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", routeId='" + routeId + '\'' +
                ", capacity=" + capacity +
                ", delayMinutes=" + delayMinutes +
                '}';
    }
}