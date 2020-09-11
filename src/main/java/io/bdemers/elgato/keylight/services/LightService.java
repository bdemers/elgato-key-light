package io.bdemers.elgato.keylight.services;

import io.bdemers.elgato.keylight.models.LightChangedEvents;
import io.bdemers.elgato.keylight.models.Lights;
import io.bdemers.elgato.keylight.models.Settings;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Track the state of the "Lights" there is always only one, but the API supports a list.
 * These changes are NOT persisted.
 */
@Service
public class LightService {

    private Lights lights;

    private final ApplicationEventPublisher applicationEventPublisher;

    public LightService(Settings settings, ApplicationEventPublisher applicationEventPublisher) {
        lights = new Lights()
                .setNumberOfLights(1)
                .add(settings.getPowerOnBehavior(), settings.getPowerOnBrightness(), settings.getPowerOnTemperature());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Lights getLights() {
        return lights;
    }

    public Lights updateLights(Lights lights) {

        // copy the old one
        Lights newLights = new Lights()
                .setNumberOfLights(this.lights.getNumberOfLights());
        this.lights.getLights().forEach(light -> newLights.add(light.getOn(), light.getBrightness(), light.getTemperature()));

        // update when non null
        if (lights != null) {
            // TODO this is ugly, and probably error prone
            for (int index = 0; index <lights.getLights().size(); index++) {
                Lights.Light updatedLight = lights.getLights().get(index);
                Lights.Light newLight = newLights.getLights().get(index);

                if (updatedLight.getOn() != null) {
                    newLight.setOn(updatedLight.getOn());
                    applicationEventPublisher.publishEvent(new LightChangedEvents.PowerChangedEvent(newLight.getOn()));
                }
                if (updatedLight.getBrightness() != null) {
                    newLight.setBrightness(updatedLight.getBrightness());
                    applicationEventPublisher.publishEvent(new LightChangedEvents.BrightnessChangedEvent(newLight.getBrightness()));
                }
                if (updatedLight.getTemperature() != null) {
                    newLight.setTemperature(updatedLight.getTemperature());
                    applicationEventPublisher.publishEvent(new LightChangedEvents.TemperatureChangedEvent(newLight.getTemperature()));
                }
            }
        }
        this.lights = newLights;
        return newLights;
    }
}
