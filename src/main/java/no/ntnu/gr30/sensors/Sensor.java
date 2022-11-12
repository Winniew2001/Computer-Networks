package no.ntnu.gr30.sensors;

/**
 * Imitates one sensor
 */
public interface Sensor {
    /**
     * Read the current value of the sensor
     * @return the current value of the sensor
     */
    double readValue();
}
