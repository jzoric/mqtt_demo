package com.jovicazoric.mqqt_java;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Mqtt{
	public static final Logger LOG = LoggerFactory.getLogger(Mqtt.class);
	public static final String BROKER_URL = "tcp://iot.eclipse.org:1883";
	public static final String SUBSCRIBE_TOPIC = "home/room/#";
	public static final String PUBLISH_TOPIC = "home/room/temp/java";
	public static final String STATUS_TOPIC = "home/room/status";
	public static final int QOS = 2;

	MqttConnectOptions connOpt;
	IMqttAsyncClient client;

	public String clientID = MqttClient.generateClientId();

	public Mqtt() {
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
	}

	public abstract void run();
}
