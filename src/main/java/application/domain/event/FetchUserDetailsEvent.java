package application.domain.event;

import application.integration.dto.EventDto;

public class FetchUserDetailsEvent extends AbstractEvent<EventDto> {

    public FetchUserDetailsEvent(EventDto payload) {
        super(payload);
    }
}