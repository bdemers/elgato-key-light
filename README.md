Stub Elgato Key Light Air Service
=================================

![status](https://img.shields.io/badge/Project%20Status-Just%20For%20Fun-orange)

This project pretends to be an Elgato Key Light, by mimicking the API and provides the same discoverable mDNS metadata.
Allowing you to add fake devices to the Elgato Control Center. When attributes of the stub light change, you can run custom code.

## Get Started

Clone it:

```sh
git clone https://github.com/bdemers/elgato-key-light
```

Run it:

```sh
./mvnw spring-boot:run
```

Verify it's running:

```
curl localhost:9123/elgato/lights
```

# Trigger Events

Trigger custom code when the temperature, brightness, or power state changes via the Elgato Control Center.

```java
@Bean
ApplicationListener<LightChangedEvents.PowerChangedEvent> powerChanged() {
    return event -> System.out.println("Power Changed: " + event.isPowerOn());
}

@Bean
ApplicationListener<LightChangedEvents.TemperatureChangedEvent> temperatureChanged() {
    return event -> System.out.println("Temperature Changed: " + event.getTemperature());
}

@Bean
ApplicationListener<LightChangedEvents.BrightnessChangedEvent> brightnessChanged() {
    return event -> System.out.println("Brightness Changed: " + event.getBrightness());
}
``` 

### NOTE: Just like the Elgato Key Lights, there is NO security in this application, all communication is plain text. 