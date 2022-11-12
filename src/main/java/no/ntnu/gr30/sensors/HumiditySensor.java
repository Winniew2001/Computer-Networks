package no.ntnu.gr30.sensors;

import no.ntnu.gr30.providers.IdProvider;
import no.ntnu.gr30.utils.SensorType;

/**
 * Represents a humidity sensor.
 */
public class HumiditySensor extends RangeRestrictedSensor{
    private static final double NORMAL_ROOM_HUMIDITY = 50;
    private static final double MIN_ROOM_HUMIDITY = 30;
    private static final double MAX_ROOM_HUMIDITY = 70;
    private final SensorType sensorType;
    private final int id;
    private String location;

    public HumiditySensor(String location){
        super(MIN_ROOM_HUMIDITY, MAX_ROOM_HUMIDITY, NORMAL_ROOM_HUMIDITY);
        this.sensorType = SensorType.HUMIDITY;
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
        return sensorType;
    }

    @Override
    public String getLocation() {
        return this.location;
    }
}
