package application.domain.event;

import application.domain.service.FetchUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FetchUserDetailsListener extends AbstractListener<FetchUserDetailsEvent> {

    @Autowired
    private FetchUserDetailsService fetchUserDetailsService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @EventListener
    public void listener(FetchUserDetailsEvent event) {
        resolver(event);
    }

    @Override
    public void handler(FetchUserDetailsEvent event) {
        var payload = event.getPayload();
        var userId = event.getPayload().getUserId();

        fetchUserDetailsService.process(userId);

        var fetchCarDetailsEvent = new FetchCarDetailsEvent(payload);
        applicationEventPublisher.publishEvent(fetchCarDetailsEvent);
    }
}
