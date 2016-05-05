# MQTT Demo
Demo examples used at [Internet of Things - MQTT](http://www.meetup.com/Coding-Bosnia/events/230484066/) meetup presentation.

## Idea
Use Raspberry Pi to send (publish) the DHT11 sensor data (temperature and humidity) to the broker. Create an MQTT client (subscribe) in JS and Java and read the data.

## Raspberry Pi setup
Publisher is written in python but before running it you should install  [Adafruit](https://github.com/adafruit/Adafruit_Python_DHT) and [Paho](https://eclipse.org/paho/clients/python/) libraries.

#### Hardware
- Raspberry PI 2
- Breadboard & 3 Jumper Wires
- 1 x DHT11 sensor
- 1 x 10K Resistor

#### How it looks like
![Image 1](/images/img_1.jpg)

![Image 2](/images/img_2.jpg)
