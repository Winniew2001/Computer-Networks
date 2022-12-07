package no.ntnu.gr30;

import no.ntnu.gr30.mqtt.MqttCli;
import no.ntnu.gr30.sensors.Sensor;
import no.ntnu.gr30.providers.SensorProvider;

/**
 * Represents our application.
 */
public class App {
    private static final long SLEEP_DURATION_MS = 2000;
    private boolean appRunning;
    double lastHumidityReading;
    double lastTemperatureReading;

    Sensor humiditySensor;
    Sensor temperatureSensor;

    MqttCli mqttCli;

    public void run() throws IllegalStateException{
        appRunning = true;
        initializeSensors();
        mqttCli = MqttCli.instance;
        mqttCli.connectToBroker();
        while (appRunning) {
            readAllSensors();
            sendDataToServer();
            powerNap();
        }
        mqttCli.disconnectFromBroker();
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
        lastHumidityReading = humiditySensor.readValue();
        lastTemperatureReading = temperatureSensor.readValue();
    }

    private void sendDataToServer() {
        try {
            mqttCli.publish(lastTemperatureReading, temperatureSensor);
            mqttCli.publish(lastHumidityReading, humiditySensor);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void powerNap(){
        try{
            Thread.sleep(SLEEP_DURATION_MS);
        } catch (InterruptedException e){
            System.out.println("Woke from nap!!!!!!!");
        }
    }

    public void disableApp() {
        appRunning = false;
    }
}
