package io.bdemers.elgato.keylight.models;

import org.springframework.context.ApplicationEvent;

public class LightChangedEvents {

    public static class PowerChangedEvent extends ApplicationEvent {

        private final boolean powerOn;

        public PowerChangedEvent(int powerOn) {
            this(powerOn == 1);
        }

        public PowerChangedEvent(boolean powerOn) {
            super(powerOn);
            this.powerOn = powerOn;
        }

        public boolean isPowerOn() {
            return powerOn;
        }
    }

    public static class TemperatureChangedEvent extends ApplicationEvent {

        private final int temperature;

        public TemperatureChangedEvent(int temperature) {
            super(temperature);
            this.temperature = temperature;
        }

        public int getTemperature() {
            return temperature;
        }
    }

    public static class BrightnessChangedEvent extends ApplicationEvent {

        private final int brightness;

        public BrightnessChangedEvent(int brightness) {
            super(brightness);
            this.brightness = brightness;
        }

        public int getBrightness() {
            return brightness;
        }
    }
}
