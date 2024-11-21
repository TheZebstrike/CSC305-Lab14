package org.example;

import org.eclipse.paho.client.mqttv3.*;

public class PubSub implements MqttCallback {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC_1 = "cal-poly/ignore";
    private final static String TOPIC_2 = "cal-poly/read";
    private final static String CLIENT_ID = "jgs-twoInOne";

    public static void main(String[] args) {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            PubSub pubSub = new PubSub();
            client.setCallback(pubSub);
            client.connect();
            // publishing to two topics but subscribing to only one
            client.subscribe(TOPIC_2);
            System.out.println("Connected to BROKER: " + BROKER);
            int counter = 0;
            while (true) {
                String content1 = "this is message to ignore " + counter;
                String content2 = "this is message to read " + counter;
                MqttMessage message1 = new MqttMessage(content1.getBytes());
                message1.setQos(2);
                MqttMessage message2 = new MqttMessage(content1.getBytes());
                message2.setQos(2);
                if (client.isConnected()) {
                    client.publish(TOPIC_1, message1);
                    client.publish(TOPIC_2, message2);
                }
                counter++;
                System.out.println("Message published: " + content1);
                System.out.println("Message published: " + content2);
                Thread.sleep(5000);
            }
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(">> Message arrived. Topic: " + s +
                " Message: " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}