package io.bdemers.elgato.keylight.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lights {

    private List<Light> lights = new ArrayList<>();

    private Integer numberOfLights;

    public Lights add(int on, int brightness, int temperature) {
        lights.add(new Light()
                .setOn(on)
                .setBrightness(brightness)
                .setTemperature(temperature));
        return this;
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Light {
        private Integer brightness;
        private Integer on ;
        private Integer temperature;
    }
}
