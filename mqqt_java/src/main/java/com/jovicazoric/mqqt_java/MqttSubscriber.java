package com.jovicazoric.mqqt_java;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber extends Mqtt implements MqttCallback{

	public static void main(String[] args) {
		new MqttSubscriber().run();
	}
	@Override
	public void run() {

		try {
//			clientID = "cc";
			client = new MqttAsyncClient(BROKER_URL, clientID, new MemoryPersistence());
			client.setCallback(this);
			client.connect(connOpt).waitForCompletion();

			client.subscribe(SUBSCRIBE_TOPIC, QOS).waitForCompletion();

		} catch (MqttException e) {
			LOG.error("Error: "+e.getMessage());
			System.exit(-1);
		}

	}

	@Override
	public void connectionLost(Throwable arg0) {
		LOG.error("Connection lost!");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		LOG.info("Topic: "+topic);
		LOG.info("Message: "+ new String(message.getPayload()));
	}

}
