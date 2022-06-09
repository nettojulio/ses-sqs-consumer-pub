package e8ilab2.sessqsconsumer.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

public class AWSCredentials {
    public static AwsCredentialsProvider awsCredentialsDispatcher() {

        AwsCredentialsProvider credentialsProvider = () -> new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return System.getenv("AWS_ACCESS_KEY");
            }

            @Override
            public String secretAccessKey() {
                return System.getenv("AWS_SECRET_KEY");
            }
        };
        return credentialsProvider;
    }
}
