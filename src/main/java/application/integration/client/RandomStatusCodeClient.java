package application.integration.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RandomStatusCodeClient {

    @Value("${integration.random-status-code.url}")
    private String RandomStatusCodeUrl;

    @Autowired
    private WebClient webClient;

    public String getStatusCode() {
        return webClient.get()
                .uri(RandomStatusCodeUrl)
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .toString();
    }
}