package com.proiect.pos.repository;

import com.proiect.pos.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Integer> {
}
