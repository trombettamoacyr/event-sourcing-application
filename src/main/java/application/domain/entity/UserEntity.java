package application.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;

    private String name;

    private String email;

    private String carId;

    @CreatedDate
    private LocalDateTime createdAt;

}