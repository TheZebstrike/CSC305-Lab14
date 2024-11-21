package org.example;

import org.eclipse.paho.client.mqttv3.*;
import java.awt.*;

public class MQTTManager implements MqttCallback {
    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "click-and-draw/points";
    private final static String CLIENT_ID = MqttClient.generateClientId();

    private static MQTTManager instance;
    private MqttClient client;

    private MQTTManager() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID);
            client.setCallback(this);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);
            client.subscribe(TOPIC);
            System.out.println("Subscribed to TOPIC: " + TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MQTTManager getInstance() {
        if (instance == null) {
            instance = new MQTTManager();
        }
        return instance;
    }

    public void publishPoint(Point point) {
        try {
            String content = point.x + "," + point.y;
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(2);
            if (client.isConnected()) {
                client.publish(TOPIC, message);
                System.out.println("Message published: " + content);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String content = new String(mqttMessage.getPayload());
        System.out.println(">> Message arrived. Topic: " + s +
                " Message: " + new String(mqttMessage.getPayload()));

        String[] parts = content.split(",");
        if (parts.length == 2) {
            try {
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                Point point = new Point(x, y);

                Repository.getInstance().addPoint(point);
            } catch (NumberFormatException e) {
                System.err.println("Invalid point data: " + content);
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
