package e8ilab2.sessqsconsumer;

import e8ilab2.sessqsconsumer.services.SQSService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SesSqsConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SesSqsConsumerApplication.class, args);
		while(true){
			SQSService.messageReader();
			// Thread.sleep(1000); // Desabilitado por causa do Long Polling para econmizar $$
		}
	}

}
