package org.ouvio.simple.producer;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailProducer {

    @Value("${rabbit.exchange}")
    private String TOPIC_EXCHANGE_NAME;

    @Value("${rabbit.routing}")
    private String ROUTING_KEY;

    private RabbitTemplate template;

    @Autowired
    public EmailProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendEmail(EmailDTO email) {
        log.info("Enviando correo");
        this.template.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, email);
    }
}
