package application.domain.event;

import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;

public class NotifyTopicListener extends AbstractListener<NotifyTopicEvent> {

    @Value("${cloud.aws.sns.event-finished-topic-arn")
    private String topicArn;

    @Autowired
    private AmazonSNSClient snsClient;

    @Override
    @EventListener
    public void listener(NotifyTopicEvent event) {
        resolver(event);
    }

    @Override
    public void handler(NotifyTopicEvent event) {
        var message = event.getPayload().toString();

        snsClient.publish(topicArn, message);
    }
}