package no.ntnu.gr30;

import no.ntnu.gr30.sensors.Sensor;

public class App {
    private static final long SLEEP_DURATION_MS = 2000;
    double lastHumidityReading;
    double lastTemperatureReading;

    Sensor humiditySensor;
    Sensor temperatureSensor;

    public void run() throws IllegalStateException{
        initializeSensors();
        while (true) {
            readAllSensors();
            sendDataToServer();
            powerNap();
        }
    }

    private void initializeSensors() throws IllegalStateException{
        SensorProvider sensorProvider = SensorProvider.getInstance();
        humiditySensor = sensorProvider.getHumiditySensor();
        temperatureSensor = sensorProvider.getTemperatureSensor();
        if (humiditySensor == null || temperatureSensor == null){
            throw new IllegalStateException("Sensor not found");
        }
    }

    private void readAllSensors(){
        System.out.println("Reading sensor data...");
        lastHumidityReading = humiditySensor.readValue();
        lastTemperatureReading = temperatureSensor.readValue();
    }

    private void sendDataToServer() {
        System.out.println("Sending data to server");
        System.out.println(" humi: " + lastHumidityReading + "%");
        System.out.println(" temp: " + lastTemperatureReading + "%");
    }

    private void powerNap(){
        try{
            Thread.sleep(SLEEP_DURATION_MS);
        } catch (InterruptedException e){
            System.out.println("Woke from nap!!!!!!!");
        }
    }
}
