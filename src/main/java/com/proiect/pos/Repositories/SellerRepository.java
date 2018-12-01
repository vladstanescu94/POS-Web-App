package com.proiect.pos.Repositories;

import com.proiect.pos.Entities.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Seller findByFirstName(String firstName);
}
