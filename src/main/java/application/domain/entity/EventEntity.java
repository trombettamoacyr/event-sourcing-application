package application.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "event")
public class EventEntity {

    @Id
    private String id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;
}