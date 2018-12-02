package com.proiect.pos.repository;

import com.proiect.pos.model.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByUsername(String username);
}
