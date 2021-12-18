package application.integration.client;

import application.integration.dto.CarDetailsDto;
import application.integration.dto.CarDto;
import application.integration.dto.UserDetailsDto;
import application.integration.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MyFakeApiClient {

    @Value("${integration.myfakeapi.users-url}")
    private String usersUrl;

    @Value("${integration.myfakeapi.cars-url}")
    private String carsUrl;

    @Autowired
    private WebClient webClient;

    public UserDetailsDto getUserDetails(String userId) {
        return webClient.get()
                .uri(usersUrl, uriBuilder -> uriBuilder.pathSegment(userId).build())
                .retrieve()
                .bodyToMono(UserDto.class)
                .block()
                .getUserDetailsDto();
    }

    public CarDetailsDto getCarDetails(String carId) {
        return webClient.get()
                .uri(carsUrl, uriBuilder -> uriBuilder.pathSegment(carId).build())
                .retrieve()
                .bodyToMono(CarDto.class)
                .block()
                .getCarDetailsDto();
    }
}