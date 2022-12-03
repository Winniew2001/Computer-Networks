package no.ntnu.gr30;

import no.ntnu.gr30.mqtt.MqttBroker;

public class App {
    MqttBroker mqttBroker;
    public void run() {
        mqttBroker = MqttBroker.instance;
    }
}
