package io.bdemers.elgato.keylight.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bdemers.elgato.keylight.models.AccessoryInfo;
import io.bdemers.elgato.keylight.models.Lights;
import io.bdemers.elgato.keylight.models.Settings;
import io.bdemers.elgato.keylight.services.LightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mocks out the expected Elgato Key Light REST API
 */
@RestController
public class ElgatoKeyLightController {

    private final ObjectMapper objectMapper;
    private final LightService lightService;
    private final AccessoryInfo accessoryInfo;
    private final Settings settings;

    public ElgatoKeyLightController(LightService lightService, AccessoryInfo accessoryInfo, Settings settings, ObjectMapper objectMapper) {
        this.lightService = lightService;
        this.objectMapper = objectMapper;
        this.settings = settings;
        this.accessoryInfo = accessoryInfo;
    }

    @GetMapping("/elgato/accessory-info")
    AccessoryInfo accessoryInfo() {
        return accessoryInfo;
    }

    @PutMapping("/elgato/accessory-info")
    AccessoryInfo accessoryInfo(@RequestBody String accessoryInfo) throws JsonProcessingException {
        // TODO this isn't persisted, but the Elgato app will keep calling PUT until it's updated
        this.accessoryInfo.setDisplayName(parse(accessoryInfo, AccessoryInfo.class).getDisplayName());
        return this.accessoryInfo;
    }

    @GetMapping("/elgato/lights/settings")
    Settings settings() {
        return settings;
    }

    @GetMapping("/elgato/lights")
    Lights lights() {
        return lightService.getLights();
    }

    @PutMapping("/elgato/lights")
    Lights updateLights(@RequestBody String lights) throws JsonProcessingException {
        return lightService.updateLights(parse(lights, Lights.class));
    }

    /**
     * The mobile app doesn't set content type for PUT requests.  There must be a _better_ way to handle this
     * In Spring, but for now, just call objectMapper.readValue as a work around.
     */
    <T> T parse(String input, Class<T> type) throws JsonProcessingException {
        return objectMapper.readValue(input, type);
    }
}
