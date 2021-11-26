package application.domain.event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("rawtypes")
public abstract class AbstractListener<T extends AbstractEvent> {

    private static final String NEW_EVENT = "START EVENT {} - userId {}";

    public abstract void listener(T event);

    public abstract void handler(T event);

    public void resolver(T event) {

        var eventName = event.getClass().getSimpleName();
        var userId = event.getUserId();

        log.info(NEW_EVENT, eventName, userId);

        try {
            handler(event);
            log.info("OK");
        } catch (Exception ex) {
            log.info("ERRO");
        }
    }
}
