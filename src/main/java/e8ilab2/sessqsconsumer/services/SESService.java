package e8ilab2.sessqsconsumer.services;

import e8ilab2.sessqsconsumer.dto.PedidoDTO;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import javax.mail.MessagingException;
import java.io.IOException;

import static e8ilab2.sessqsconsumer.services.AWSCredentials.awsCredentialsDispatcher;
import static e8ilab2.sessqsconsumer.services.SESSendMail.send;

public class SESService {
    public static void sendMessage(String email, PedidoDTO pedidoDTO) {

        Region region = Region.US_EAST_1;
        SesClient client = SesClient.builder()
                .credentialsProvider(awsCredentialsDispatcher())
                .region(region)
                .build();

        String bodyText = "Seu pedido foi concluido!";

        String bodyHTML = "<html>"
                + "<head></head>"
                + "<body>"
                + "<h1>Olá " + pedidoDTO.getUsuarioName() + "!</h1>"
                + "<h1>Seu pedido ID: " + pedidoDTO.getId() + " acaba de ser concluido!</h1>"
                + "</body>"
                + "</html>";

        try {
            send(client, System.getenv("AWS_SES_EMAIL_SENDER"), email, "Pedido confirmado",
                    bodyText, bodyHTML);
            client.close();

            System.out.println("Email enviado.");

        } catch (IOException | MessagingException e) {
            e.getStackTrace();
        }
    }
}
