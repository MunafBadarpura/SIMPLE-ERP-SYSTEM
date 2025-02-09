package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.configs.rabbit.RabbitMQProducer;
import com.munaf.ERP_SYSTEM.dtos.InvoiceDTO;
import com.munaf.ERP_SYSTEM.entities.Invoice;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.InvoiceService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InvoiceServiceIMPL implements InvoiceService {

    private final MasterRepo masterRepo;
    private final RabbitMQProducer rabbitMQProducer;

    public InvoiceServiceIMPL(MasterRepo masterRepo, RabbitMQProducer rabbitMQProducer) {
        this.masterRepo = masterRepo;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId).
                orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }

    @Override
    public ResponseModel createInvoice(Long userId, InvoiceDTO invoiceDTO) {
        User user = getUserWithId(userId);

        Invoice invoice = invoiceDTO.invoiceDtoToInvoice();
        invoice.setUser(user);
        Invoice savedInvoice = masterRepo.getInvoiceRepo().save(invoice);

        user.getInvoices().add(savedInvoice);
        masterRepo.getUserRepo().save(user);

        // SENDING MESSAGE
        InvoiceDTO invoiceMessage = new InvoiceDTO(
                savedInvoice.getId(),
                savedInvoice.getInvoiceType(),
                savedInvoice.getInvoiceAmount(),
                savedInvoice.getCreatedAt()
        );

        rabbitMQProducer.sendMessage(invoiceMessage);
        return CommonResponse.OK(InvoiceDTO.invoiceToInvoiceDTO(savedInvoice));
    }

//    public void sendInvoiceMessage(Invoice savedInvoice) {
//        HashMap<String, Object> message = new HashMap<>();
//        message.put("Invoice Id", savedInvoice.getId());
//        message.put("Invoice Type", savedInvoice.getInvoiceType());
//        message.put("Invoice Amount", savedInvoice.getInvoiceAmount());
//        message.put("Invoice CreatedAt", savedInvoice.getCreatedAt());
//        rabbitMQProducer.sendMessage(message);
//    }

    @Override
    public ResponseModel getInvoiceById(Long userId, Long invoiceId) {
        User user = getUserWithId(userId);

        Invoice invoice = masterRepo.getInvoiceRepo().findByIdAndUserId(invoiceId, userId).orElseThrow(() -> new ResourceNotFound("Customer Not Exists"));
        return CommonResponse.OK(InvoiceDTO.invoiceToInvoiceDTO(invoice));
    }

    @Override
    public ResponseModel getAllInvoice(Long userId) {
        User user = getUserWithId(userId);
        List<Invoice> invoices = masterRepo.getInvoiceRepo().findAllByUserId(userId);

        List<InvoiceDTO> invoiceDTOS = invoices
                .stream()
                .map(invoice -> InvoiceDTO.invoiceToInvoiceDTO(invoice))
                .toList();

        return CommonResponse.OK(invoiceDTOS);
    }
}
