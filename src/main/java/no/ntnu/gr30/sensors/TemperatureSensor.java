package no.ntnu.gr30.sensors;

import no.ntnu.gr30.providers.IdProvider;
import no.ntnu.gr30.utils.SensorType;

public class TemperatureSensor extends RangeRestrictedSensor {
    private static final double NORMAL_ROOM_TEMPERATURE = 25;
    private static final double MIN_ROOM_TEMPERATURE = 0;
    private static final double MAX_ROOM_TEMPERATURE = 50;
    private final int id;
    private final SensorType sensorType;
    private String location;

    public TemperatureSensor(String location) {
        super(MIN_ROOM_TEMPERATURE, MAX_ROOM_TEMPERATURE, NORMAL_ROOM_TEMPERATURE);
        this.sensorType = SensorType.TEMPERATURE;
        this.id = IdProvider.getInstance().assignId();
        this.location = location.toLowerCase();
    }

    public void setLocation(String location) {
        this.location = location.toLowerCase();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public SensorType getSensorType() {
        return this.sensorType;
    }

    @Override
    public String getLocation() {
        return this.location;
    }
}
