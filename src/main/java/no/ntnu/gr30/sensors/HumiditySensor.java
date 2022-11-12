package no.ntnu.gr30.sensors;

public class HumiditySensor extends RangeRestrictedSensor{
    private static final double NORMAL_ROOM_HUMIDITY = 50;
    private static final double MIN_ROOM_HUMIDITY = 30;
    private static final double MAX_ROOM_HUMIDITY = 70;

    public HumiditySensor(){
        super(MIN_ROOM_HUMIDITY, MAX_ROOM_HUMIDITY, NORMAL_ROOM_HUMIDITY);
    }
}
