package com.proiect.pos.repository;

import com.proiect.pos.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    Product findById(int id);
    Product[] findAllByStockGreaterThanEqual(int amount);
}
