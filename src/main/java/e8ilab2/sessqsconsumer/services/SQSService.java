package e8ilab2.sessqsconsumer.services;

import com.google.gson.Gson;
import e8ilab2.sessqsconsumer.dto.PedidoDTO;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

import static e8ilab2.sessqsconsumer.services.AWSCredentials.awsCredentialsDispatcher;

public class SQSService {
    public static void messageReader() {

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(awsCredentialsDispatcher())
                .build();

        // ===== Busca uma Fila =====
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("sqs-e8-desafio2")  // ler da fila padrão
                .queueOwnerAWSAccountId("755977887883").build();
        GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);

        List<Message> messages = receiveMessages(sqsClient, createResult.queueUrl());
        // System.out.println("Quantidade de mensagens: " + messages.size());
        for (Message mess : messages) {
            System.out.println("Mensagem: " + mess.body());

            var jsonString = mess.body();
            PedidoDTO pedidoDTO = new Gson().fromJson(jsonString, PedidoDTO.class);
            System.err.println(pedidoDTO.getUsuarioEmail());
            //SESService.sendMessage("Mensagem: " + LocalDate.now(), pedidoDTO.getUsuarioEmail(), pedidoDTO);
        }

        deleteMessages(sqsClient, createResult.queueUrl(), messages);

        sqsClient.close();
    }

    public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(20) // Long Polling Explicar conceito para econmizar $$
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
