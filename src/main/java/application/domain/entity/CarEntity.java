package application.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "car")
public class CarEntity {

    @Id
    private String id;

    private String brand;

    private String model;

    @CreatedDate
    private LocalDateTime createdAt;

}
