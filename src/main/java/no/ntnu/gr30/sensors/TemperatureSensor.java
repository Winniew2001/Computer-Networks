package no.ntnu.gr30.sensors;

public class TemperatureSensor extends RangeRestrictedSensor {
    private static final double NORMAL_ROOM_TEMPERATURE = 25;
    private static final double MIN_ROOM_TEMPERATURE = 0;
    private static final double MAX_ROOM_HUMIDITY = 50;

    public TemperatureSensor(){
        super(MIN_ROOM_TEMPERATURE, MAX_ROOM_HUMIDITY, NORMAL_ROOM_TEMPERATURE);
    }
}
