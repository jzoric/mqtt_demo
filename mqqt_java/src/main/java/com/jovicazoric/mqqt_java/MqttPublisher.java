package com.jovicazoric.mqqt_java;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisher extends Mqtt {
	public static void main(String[] args) {
		new MqttPublisher().run();
	}
	@Override
	public void run() {
		try {
//			connOpt.setWill(STATUS_TOPIC, "Java client is dead!".getBytes(), QOS, true);
			client = new MqttAsyncClient(BROKER_URL, clientID, new MemoryPersistence());
			client.connect(connOpt).waitForCompletion();

			MqttMessage message = new MqttMessage();
			message.setPayload("21;21".getBytes());
			message.setQos(QOS);

			client.publish(PUBLISH_TOPIC, message).waitForCompletion();
//			client.disconnect();

		} catch (MqttException e) {
			LOG.error("Error: "+e.getMessage());
			System.exit(-1);
		}
	}

}
