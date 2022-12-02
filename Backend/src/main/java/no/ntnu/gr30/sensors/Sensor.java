package no.ntnu.gr30.sensors;

import no.ntnu.gr30.utils.SensorType;

/**
 * Imitates one sensor
 */
public interface Sensor {
    /**
     * Read the current value of the sensor
     * @return the current value of the sensor
     */
    double readValue();

    int getId();

    SensorType getSensorType();

    String getLocation();
}
