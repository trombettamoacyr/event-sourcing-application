package application.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfiguration {

    @Value("${cloud.aws.credentials.access-key")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key")
    private String secretKey;

    @Value("${cloud.aws.region.static")
    private String region;

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
                .withClientConfiguration(new ClientConfiguration())
                .build();
    }

    private BasicAWSCredentials awsCredentialsProvider() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
