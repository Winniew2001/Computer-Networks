package no.ntnu.gr30.mqtt;

import io.vertx.core.Vertx;
import io.vertx.mqtt.MqttServer;

public class MqttBroker {
    private Vertx vertx = Vertx.vertx();
    MqttServer mqttBrokerServer = MqttServer.create(vertx);
}
