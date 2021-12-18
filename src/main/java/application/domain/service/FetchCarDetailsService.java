package application.domain.service;

import application.domain.entity.CarEntity;
import application.domain.repository.CarRepository;
import application.integration.client.MyFakeApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchCarDetailsService {

    @Autowired
    private MyFakeApiClient myFakeApiClient;

    @Autowired
    private CarRepository carRepository;

    public void process(String carId) {

        var carDetailsDto = myFakeApiClient.getCarDetails(carId);
        var brand = carDetailsDto.getBrand();
        var model = carDetailsDto.getModel();

        var car = CarEntity.builder()
                .brand(brand)
                .model(model)
                .build();

        carRepository.save(car);
    }
}
