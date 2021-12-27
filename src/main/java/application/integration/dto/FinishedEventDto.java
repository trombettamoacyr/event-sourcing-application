package application.integration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinishedEventDto {

    private UserDetailsDto user;

    private CarDetailsDto car;

    private String statusCode;

}
