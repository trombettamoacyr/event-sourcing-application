package application.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "event")
public class EventEntity {

    @Id
    private String id;

    private String name;

    private Object payload;

    @Builder.Default
    private boolean error = false;

    private String exception;

    private String stackTrace;

    @CreatedDate
    private LocalDateTime createdAt;
}