package io.bdemers.elgato.keylight.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
@ConfigurationProperties("elgato.lights.settings")
public class Settings {

    private Integer colorChangeDurationMs = 100;
    private Integer powerOnBehavior = 1;
    private Integer powerOnBrightness = 20;
    private Integer powerOnTemperature = 213;
    private Integer switchOffDurationMs = 300;
    private Integer switchOnDurationMs = 100;
}
