package e8ilab2.sessqsconsumer.services;

import com.google.gson.Gson;
import e8ilab2.sessqsconsumer.dto.PedidoDTO;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

import static e8ilab2.sessqsconsumer.services.AWSCredentials.awsCredentialsDispatcher;
import static e8ilab2.sessqsconsumer.services.SESService.sendMessage;

public class SQSService {
    public static void messageReader() {

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(awsCredentialsDispatcher())
                .build();

        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName(System.getenv("AWS_SQS_QUEUE_NAME"))
                .queueOwnerAWSAccountId(System.getenv("AWS_SQS_QUEUE_ACCOUNT_ID")).build();
        GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);

        List<Message> messages = receiveMessages(sqsClient, createResult.queueUrl());

        for (Message mess : messages) {
            var jsonString = mess.body();
            PedidoDTO pedidoDTO = new Gson().fromJson(jsonString, PedidoDTO.class);
            sendMessage(pedidoDTO.getUsuarioEmail(), pedidoDTO);
        }

        deleteMessages(sqsClient, createResult.queueUrl(), messages);

        sqsClient.close();
    }

    public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(20)
                .maxNumberOfMessages(5)
                .build();
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        return messages;
    }

    public static void deleteMessages(SqsClient sqsClient, String queueUrl, List<Message> messages) {
        for (Message message : messages) {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(message.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);
        }
    }
}
