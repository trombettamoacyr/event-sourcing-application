package application.domain.event;

import application.integration.dto.EventDto;

public class RandomStatusCodeEvent extends AbstractEvent<EventDto> {

    public RandomStatusCodeEvent(EventDto payload) {
        super(payload);
    }
}