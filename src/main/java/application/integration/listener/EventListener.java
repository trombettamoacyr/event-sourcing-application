package application.integration.listener;

import application.integration.dto.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SqsListener(value = "${cloud.aws.sqs.event-created-queue-url}")
    public void eventListener(String message) throws JsonProcessingException {
        var newEvent = objectMapper.readValue(message, EventMessage.class);

        System.out.println(newEvent);
    }
}
