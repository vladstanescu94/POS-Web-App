package com.proiect.pos.Repositories;

import com.proiect.pos.Entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
//    Product findById(Long ID);
}
