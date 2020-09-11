package io.bdemers.elgato.keylight.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
@ConfigurationProperties("elgato.lights.info")
public class AccessoryInfo {

    private String displayName = "Fake Light";
    private List<String> features = List.of("lights");
    private int firmwareBuildNumber = 195;
    private String firmwareVersion = "1.0.3";
    private int hardwareBoardType = 200;
    private String productName = "Elgato Key Light Air";
    private String serialNumber = "CW31J1A00183";
}
