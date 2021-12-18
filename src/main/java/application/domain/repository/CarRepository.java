package application.domain.repository;

import application.domain.entity.CarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<CarEntity, String> {
}
