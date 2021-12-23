package application.domain.event;

import application.domain.entity.EventEntity;
import application.domain.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@SuppressWarnings("rawtypes")
public abstract class AbstractListener<T extends AbstractEvent> {

    private static final String NEW_EVENT = "START EVENT {}";
    private static final String SAVE_EVENT = "SAVE EVENT {}";
    private static final String ERROR_EVENT = "ERROR EVENT {}";

    private static final String EVENT_PACKAGE = "application.domain.event";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public abstract void listener(T event);

    public abstract void handler(T event);

    public void resolver(T event) {

        var eventName = event.getClass().getSimpleName();
        log.info(NEW_EVENT, eventName);

        try {
            handler(event);
            saveEvent(event);
            log.info(SAVE_EVENT, eventName);

        } catch (Exception ex) {
            saveEventError(event, ex);
            log.info(ERROR_EVENT, eventName);
        }
    }

    private void saveEvent(T event) {
        var name = event.getClass().getSimpleName();
        var payload = event.getPayload();

        var eventEntity = EventEntity.builder()
                .name(name)
                .payload(payload)
                .build();

        eventRepository.save(eventEntity);
    }

    private void saveEventError(T event, Exception ex) {
        var name = event.getClass().getSimpleName();
        var payload = event.getPayload();

        var eventEntity = EventEntity.builder()
                .name(name)
                .payload(payload)
                .error(true)
                .exception(ex.getMessage())
                .stackTrace(ExceptionUtils.getStackTrace(ex))
                .build();

        eventRepository.save(eventEntity);

        var abstractEvent = getEvent(name, payload);
        applicationEventPublisher.publishEvent(abstractEvent);
    }

    private AbstractEvent getEvent(String eventName, Object payload) {
        try {
            var clazz = Class.forName(EVENT_PACKAGE.concat(".").concat(eventName));
            var constructor = clazz.getDeclaredConstructors()[0];
            return (AbstractEvent) constructor.newInstance(payload);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}