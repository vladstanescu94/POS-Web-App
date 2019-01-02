package com.proiect.pos.service;

import com.proiect.pos.model.Invoice;
import com.proiect.pos.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("invoiceService")
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){this.invoiceRepository=invoiceRepository;}

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
