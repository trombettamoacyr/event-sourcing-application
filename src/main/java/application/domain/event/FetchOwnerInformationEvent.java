package application.domain.event;

import application.integration.dto.EventDto;

public class FetchOwnerInformationEvent extends AbstractEvent<EventDto> {

    public FetchOwnerInformationEvent(EventDto payload) {
        super(payload);
    }
}