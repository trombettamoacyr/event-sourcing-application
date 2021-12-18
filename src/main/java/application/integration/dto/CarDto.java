package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarDto {

    @JsonProperty(value = "Car")
    private CarDetailsDto carDetailsDto;
}
