package application.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfiguration {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.sns.service-endpoint}")
    private String serviceEndpoint;

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

    @Primary
    @Bean
    public AmazonSNSClient snsClient() {
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider()))
                .withEndpointConfiguration(endpointConfiguration())
                .build();
    }

    private BasicAWSCredentials awsCredentialsProvider() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, region);
    }
}