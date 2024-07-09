package com.clientapi.clientapi.services;

import com.clientapi.clientapi.entities.Invoice;
import com.clientapi.clientapi.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesRepository invoicesRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoicesRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoicesRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoicesRepository.findById(id);
    }

    public void deleteInvoice(Long id) {
        invoicesRepository.deleteById(id);
    }
}