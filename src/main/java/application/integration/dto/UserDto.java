package application.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {

    @JsonProperty(value = "User")
    private UserDetailsDto userDetailsDto;

}
