package application.domain.event;

import application.integration.client.RandomStatusCodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RandomStatusCodeListener extends AbstractListener<RandomStatusCodeEvent> {

    @Autowired
    private RandomStatusCodeClient randomStatusCodeClient;

    @Override
    @EventListener
    public void listener(RandomStatusCodeEvent event) {
        resolver(event);
    }

    @Override
    public void handler(RandomStatusCodeEvent event) {

        randomStatusCodeClient.getStatusCode();

    }
}
