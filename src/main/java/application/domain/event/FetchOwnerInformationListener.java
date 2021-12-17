package application.domain.event;

import application.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FetchOwnerInformationListener extends AbstractListener<FetchOwnerInformationEvent> {

    @Autowired
    private EventRepository eventRepository;

    @EventListener
    public void listener(FetchOwnerInformationEvent event) {
        resolver(event);
    }

    @Override
    public void handler(FetchOwnerInformationEvent event) {

    }
}
