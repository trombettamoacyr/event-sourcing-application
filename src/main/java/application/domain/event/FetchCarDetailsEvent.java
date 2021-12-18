package application.domain.event;

import application.integration.dto.EventDto;

public class FetchCarDetailsEvent extends AbstractEvent<EventDto> {

    public FetchCarDetailsEvent(EventDto payload) {
        super(payload);
    }
}