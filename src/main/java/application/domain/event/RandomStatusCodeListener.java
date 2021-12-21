package application.domain.event;

import application.domain.service.RandomStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RandomStatusCodeListener extends AbstractListener<RandomStatusCodeEvent> {

    @Autowired
    private RandomStatusCodeService randomStatusCodeService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @EventListener
    public void listener(RandomStatusCodeEvent event) {
        resolver(event);
    }

    @Override
    public void handler(RandomStatusCodeEvent event) {
        var eventDto = event.getPayload();

        var finishedEventDto = randomStatusCodeService.process(eventDto);

        var notifyTopicEvent = new NotifyTopicEvent(finishedEventDto);
        applicationEventPublisher.publishEvent(notifyTopicEvent);
    }
}