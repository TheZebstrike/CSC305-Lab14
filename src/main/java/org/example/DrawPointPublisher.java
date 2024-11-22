package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.awt.*;

public class DrawPointPublisher implements MqttCallback, Runnable{
    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "draw_points_server";
    private final static String TOPIC_TO_SUB = "draw_points_client";
    private final static String CLIENT_ID = "publisher";

    private MqttClient client;

    public DrawPointPublisher() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID);
            client.setCallback(this);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);
            client.subscribe(TOPIC_TO_SUB);
            System.out.println("Subscribed to TOPIC: " + TOPIC_TO_SUB);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                publishPoints();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void publishPoints() {
        try {
            String content = Repository.getInstance().pointsToString();
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(2);
            if (client.isConnected()) {
                client.publish(TOPIC, message);
                System.out.println("server: " + content);
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
        Repository.getInstance().parseAndSetPoints(content);
        Repository.getInstance().repaint();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
