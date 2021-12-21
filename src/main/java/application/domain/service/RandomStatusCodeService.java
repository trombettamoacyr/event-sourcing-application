package application.domain.service;

import application.domain.entity.CarEntity;
import application.domain.entity.UserEntity;
import application.domain.repository.CarRepository;
import application.domain.repository.UserRepository;
import application.integration.client.RandomStatusCodeClient;
import application.integration.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomStatusCodeService {

    @Autowired
    private RandomStatusCodeClient randomStatusCodeClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public FinishedEventDto process(EventDto eventDto) {
        var userId = eventDto.getUserId();
        var carId = eventDto.getCarId();

        var statusCode = randomStatusCodeClient.getStatusCode();

        var user = userRepository.findById(userId).get();
        var car = carRepository.findById(carId).get();

        return buildFinishedEvent(user, car, statusCode);
    }

    private FinishedEventDto buildFinishedEvent(UserEntity user, CarEntity car, String statusCode) {
        var userDetailsDto = UserDetailsDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        var carDetailsDto = CarDetailsDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .build();

        return FinishedEventDto.builder()
                .user(userDetailsDto)
                .car(carDetailsDto)
                .statusCode(statusCode)
                .build();
    }
}