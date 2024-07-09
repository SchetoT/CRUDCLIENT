package com.clientapi.clientapi.repositories;

import com.clientapi.clientapi.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
}