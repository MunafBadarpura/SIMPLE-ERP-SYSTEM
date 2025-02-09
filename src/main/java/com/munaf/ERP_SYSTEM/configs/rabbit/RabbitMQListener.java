package com.munaf.ERP_SYSTEM.configs.rabbit;

import com.munaf.ERP_SYSTEM.dtos.InvoiceDTO;
import com.munaf.ERP_SYSTEM.entities.Invoice;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(InvoiceDTO message) {
        System.out.println("Received Invoice Message: " + message);
    }
}
