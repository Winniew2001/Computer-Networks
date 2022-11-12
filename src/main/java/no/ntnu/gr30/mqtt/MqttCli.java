package no.ntnu.gr30.mqtt;

import no.ntnu.gr30.sensors.Sensor;
import no.ntnu.gr30.utils.SensorType;
import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public class MqttCli {
    private final static String TRANSPORT_PROTOCOL = "tcp";
    private final static String HOST_ADDRESS = "129.241.152.12";
    private final static String PORT_NUMBER = "1883";
    private final static String BASE_PATH = "aronshouse/sornesvaagen";
    private final static String GROUP = "group30";

    public final static MqttCli instance = new MqttCli();

    private boolean connected = false;

    private MqttClient client;
    private MqttReporter callback;
    private final MqttConnectOptions options;

    private MqttCli() {
        callback = new MqttReporter();
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
    }

    public void connectToBroker() {
        if (connected) return;

        try {
            client = new MqttClient(
                TRANSPORT_PROTOCOL + "://" + HOST_ADDRESS + ":" + PORT_NUMBER,
                UUID.randomUUID().toString());
            callback = new MqttReporter();
            client.setCallback(callback);

            IMqttToken token = client.connectWithResult(options);
            token.waitForCompletion();

            connected = true;
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MqttCli getInstance() {
        return instance;
    }

    public void publish(double value, Sensor sensor) {
        String valueString = "" + value;
        byte[] payload = valueString.getBytes();
        String sensorType = stringFromSensorType(sensor.getSensorType());
        int sensorId = sensor.getId();
        String room = sensor.getLocation();

        MqttMessage message = new MqttMessage();
        message.setQos(0);
        message.setRetained(true);
        message.setPayload(payload);

        try {
            client.publish(BASE_PATH + "/" + room + "/" + sensorType + "/" + GROUP + "/" + sensorId,
                    message);
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }

    private String stringFromSensorType(SensorType sensorType) {
        if (sensorType == SensorType.HUMIDITY) return "humidity";
        if (sensorType == SensorType.TEMPERATURE) return "temperature";
        throw new IllegalStateException("Unknown sensor type");
    }

    public void disconnectFromBroker() {
        if (!connected) return;

        try {
            client.disconnect();
            connected = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
