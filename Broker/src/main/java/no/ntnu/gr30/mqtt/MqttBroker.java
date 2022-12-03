package no.ntnu.gr30.mqtt;

import io.netty.handler.codec.mqtt.MqttProperties;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import io.vertx.mqtt.MqttTopicSubscription;
import io.vertx.mqtt.messages.codes.MqttSubAckReasonCode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MqttBroker {
    private Vertx vertx;
    private MqttServer mqttServer;

    public final static MqttBroker instance = new MqttBroker();

    private MqttBroker() {
        vertx = Vertx.vertx();
        mqttServer = MqttServer.create(vertx, mqttServerOptions());
        prepareEndpointHandlers();
        deploy();
    }

    private MqttServerOptions mqttServerOptions() {
        return new MqttServerOptions()
                .setPort(1883)
                .setUseWebSocket(true)
                .setSsl(false);
//                .setKeyCertOptions(new PemKeyCertOptions()
//                        .setKeyPath("?.pem")
//                        .setCertPath("?.pem"))
//                .setSsl(true);
    }

    private void prepareEndpointHandlers() {
        mqttServer.endpointHandler(mqttEndpoint -> {

            System.out.println("MQTT client [" + mqttEndpoint.clientIdentifier() + "] request to connect, clean session = " + mqttEndpoint.isCleanSession());

            if (mqttEndpoint.auth() != null) {
                System.out.println("[username =" + mqttEndpoint.auth().getUsername() + ", password =" + mqttEndpoint.auth().getPassword() + "]");
            }
            System.out.println(Arrays.toString(mqttEndpoint.will().getWillMessageBytes()));
            System.out.println("[properties = " + mqttEndpoint.connectProperties() + "]");
            if (mqttEndpoint.will() != null) {
                System.out.println("[will topic = " + mqttEndpoint.will().getWillTopic() + " msg = " +
                        (mqttEndpoint.will().getWillMessageBytes() != null ? new String(mqttEndpoint.will().getWillMessageBytes()) : "") +
                        " QoS = " + mqttEndpoint.will().getWillQos() + " isRetain = " + mqttEndpoint.will().isWillRetain() + "]");
            }

            System.out.println("[keep alive timeout = " + mqttEndpoint.keepAliveTimeSeconds() + "]");
            mqttEndpoint.accept(true);

            //Subscribe handler
            mqttEndpoint.subscribeHandler(mqttSubscribeMessage -> {
                List<MqttSubAckReasonCode> reasonCodes = new ArrayList<>();
                for (MqttTopicSubscription s: mqttSubscribeMessage.topicSubscriptions()){
                    System.out.println("Subscription for " + s.topicName() + " with Qos" + s.qualityOfService());
                    reasonCodes.add(MqttSubAckReasonCode.qosGranted(s.qualityOfService()));
                }
                mqttEndpoint.subscribeAcknowledge(mqttSubscribeMessage.messageId(), reasonCodes, MqttProperties.NO_PROPERTIES);
            });

            //Unsubscribe handler
            mqttEndpoint.unsubscribeHandler(mqttUnsubscribeMessage -> {
                for (String t: mqttUnsubscribeMessage.topics()){
                    System.out.println("Unsubscription for" + t);
                }

                //Acknowledge the subscriptions request
                mqttEndpoint.unsubscribeAcknowledge(mqttUnsubscribeMessage.messageId());
            });

            //Publish handler
            mqttEndpoint.publishHandler(mqttPublishMessage -> {
                System.out.println("Just received message [" + mqttPublishMessage.payload().toString(Charset.defaultCharset()) + "] with QoS [" + mqttPublishMessage.qosLevel() + "] at " + mqttPublishMessage.topicName());

                //Publish message to client
                mqttEndpoint.publish(mqttPublishMessage.topicName(),
                        mqttPublishMessage.payload(),
                        MqttQoS.AT_LEAST_ONCE,
                        false,
                        true);

                if (mqttPublishMessage.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
                    mqttEndpoint.publishAcknowledge(mqttPublishMessage.messageId());
                } else if (mqttPublishMessage.qosLevel() == MqttQoS.EXACTLY_ONCE) {
                    mqttEndpoint.publishReceived(mqttPublishMessage.messageId());
                }
            }).publishReleaseHandler(mqttEndpoint::publishComplete);

            //Publish message to client
            mqttEndpoint.publish("aronshouse/sornesvaagen/kitchen/humidity/gr30/0",
                    Buffer.buffer("H"),
                    MqttQoS.AT_LEAST_ONCE,
                    false,
                    true);

            //Specifing handlers for handling QoS 1 and 2
            mqttEndpoint.publishAcknowledgeHandler(mqttPublishMessageId -> {
                System.out.println("Received ack for message = " + mqttPublishMessageId);
            }).publishAcknowledgeHandler(mqttPublishMessageId ->{
                mqttEndpoint.publishRelease(mqttPublishMessageId);
            }).publishCompletionHandler(mqttPublishMessageId ->{
                System.out.println("Received ack for message = " + mqttPublishMessageId);
            });

            //Notification about keep alive
            mqttEndpoint.pingHandler(v -> {
                System.out.println("Ping received from client");
            });

            //Disconnect
            mqttEndpoint.disconnectMessageHandler(mqttDisconnectMessage -> {
                System.out.println("Received disconnect from client, reason code = " + mqttDisconnectMessage.code());
            });
        }).listen(mqttServerAsyncResult -> {
            if (mqttServerAsyncResult.succeeded()) {
                System.out.println("MQTT server is listening on port " + mqttServerAsyncResult.result().actualPort());
            } else {
                System.out.println("Error on starting the server");
                mqttServerAsyncResult.cause().printStackTrace();
            }
        });
    }

    private void deploy() {
        DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(10);
        this.vertx.deployVerticle("no.ntnu.gr30", deploymentOptions);
    }

    public void closeServer() {
        //Closing the server
        this.mqttServer.close(v -> {
            System.out.println("MQTT server closed");
        });
    }
}
