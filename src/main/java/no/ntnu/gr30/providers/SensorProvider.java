package no.ntnu.gr30.providers;

import no.ntnu.gr30.sensors.HumiditySensor;
import no.ntnu.gr30.sensors.Sensor;
import no.ntnu.gr30.sensors.TemperatureSensor;

public class SensorProvider {
    private final HumiditySensor humiditySensor = new HumiditySensor("kitchen");
    private final TemperatureSensor temperatureSensor = new TemperatureSensor("bedroom");

    private SensorProvider(){}
    private static final SensorProvider instance = new SensorProvider();

    public static SensorProvider getInstance(){
        return instance;
    }

    public Sensor getHumiditySensor(){
        return humiditySensor;
    }

    public Sensor getTemperatureSensor() {
        return temperatureSensor;
    }
}
