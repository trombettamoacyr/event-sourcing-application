package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    @JsonProperty(value = "user_id")
    private String externalUserId;

    @JsonProperty(value = "car_id")
    private String externalCarId;

    private String userId;

    private String carId;

}