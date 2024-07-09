package com.clientapi.clientapi.controllers;

import com.clientapi.clientapi.entities.Invoice;
import com.clientapi.clientapi.services.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

    @Autowired
    private InvoicesService invoicesService;

    @PostMapping()
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoicesService.createInvoice(invoice);
        return ResponseEntity.ok(savedInvoice);
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoicesService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long id) {
        Optional<Invoice> invoice = invoicesService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable("id") Long id) {
        invoicesService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }


}