package application.domain.event;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotifyTopicListener extends AbstractListener<NotifyTopicEvent> {

    @Value("${cloud.aws.sns.event-finished-topic-arn}")
    private String topicArn;

    @Autowired
    private AmazonSNSClient snsClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @EventListener
    public void listener(NotifyTopicEvent event) {
        resolver(event);
    }

    @Override
    public void handler(NotifyTopicEvent event) {
        var finishedEventDto = event.getPayload();
        var message = StringUtil.EMPTY_STRING;

        try {
            message = objectMapper.writeValueAsString(finishedEventDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        var publishRequest = new PublishRequest(topicArn, message);
        snsClient.publish(publishRequest);
    }
}