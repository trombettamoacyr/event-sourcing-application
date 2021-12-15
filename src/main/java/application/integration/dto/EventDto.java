package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "teste")
    private String teste;

}
