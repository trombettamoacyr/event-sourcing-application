package application.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public abstract class AbstractEvent<T> {

    protected UUID userId;
    protected T payload;

}