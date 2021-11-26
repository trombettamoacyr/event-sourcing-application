package application.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "event")
public class EventEntity {

    @MongoId
    private UUID id = UUID.randomUUID();

    @CreatedDate
    private LocalDateTime createdAt;
}