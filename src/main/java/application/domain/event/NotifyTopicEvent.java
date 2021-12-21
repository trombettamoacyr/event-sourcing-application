package application.domain.event;

import application.integration.dto.FinishedEventDto;

public class NotifyTopicEvent extends AbstractEvent<FinishedEventDto> {

    public NotifyTopicEvent(FinishedEventDto payload) {
        super(payload);
    }
}
