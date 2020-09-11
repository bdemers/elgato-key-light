package io.bdemers.elgato.keylight;

import io.bdemers.elgato.keylight.models.LightChangedEvents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class KeyLightApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeyLightApplication.class, args);
    }

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
}
