#!/usr/bin/python

import Adafruit_DHT
import time
import paho.mqtt.client as paho

sensor = Adafruit_DHT.DHT11

pin = 4


client = paho.Client()
client.will_set("home/room/status","I'm dead", 0, False)
client.connect_async("iot.eclipse.org",1883)

client.loop_start()



logfile = open('sensor.log','w')

while True:
    humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
    if humidity is not None and temperature is not None:
        print 'Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(temperature, humidity)
        data = '{0:0.1f};{1:0.1f}'.format(temperature,humidity)
        client.publish("home/room/temp", str(data), qos=1)
        #logfile.write('Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(temperature, humidity)+'\n')
    else:
        print 'Failed to get reading. Try again!'
    time.sleep(10)
