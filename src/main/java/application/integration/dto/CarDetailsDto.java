package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDetailsDto {

    @JsonProperty(value = "car")
    private String brand;

    @JsonProperty(value = "car_model")
    private String model;
}
