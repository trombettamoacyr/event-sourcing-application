package application.domain.event;

import application.domain.service.FetchCarDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FetchCarDetailsListener extends AbstractListener<FetchCarDetailsEvent> {

    @Autowired
    private FetchCarDetailsService fetchCarDetailsService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @EventListener
    public void listener(FetchCarDetailsEvent event) {
        handler(event);
    }

    @Override
    public void handler(FetchCarDetailsEvent event) {
        var payload = event.getPayload();
        var carId = payload.getCarId();

        fetchCarDetailsService.process(carId);

        var randomStatusCodeEvent = new RandomStatusCodeEvent(payload);
        applicationEventPublisher.publishEvent(randomStatusCodeEvent);
    }
}
