package application.domain.event;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public abstract class EventAbstract<T> extends ApplicationEvent {

    protected UUID userId;
    protected T payload;

    public EventAbstract(Object source, UUID userId, T payload) {
        super(source);
        this.userId = userId;
        this.payload = payload;
    }

    public UUID getUserId() {
        return userId;
    }

    public T getPayload() {
        return payload;
    }
}