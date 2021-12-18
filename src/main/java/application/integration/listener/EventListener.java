package application.integration.listener;

import application.domain.event.FetchUserDetailsEvent;
import application.integration.dto.EventDto;
import application.integration.dto.EventMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SqsListener(value = "${cloud.aws.sqs.event-created-queue-url}")
    public void eventListener(String message) throws JsonProcessingException {
        var eventJson = objectMapper.readValue(message, EventMessageDto.class);
        var eventDto = objectMapper.readValue(eventJson.getEventJson(), EventDto.class);

        var fetchOwnerInformationEvent = new FetchUserDetailsEvent(eventDto);
        applicationEventPublisher.publishEvent(fetchOwnerInformationEvent);
    }
}
