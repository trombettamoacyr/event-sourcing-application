package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    @JsonProperty(value = "car_id")
    private String carId;

    @JsonProperty(value = "owner_id")
    private String ownerId;

    @JsonProperty(value = "image_id")
    private String imageId;

}
