package com.proiect.pos.service;

import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Product findById(int id){return productRepository.findById(id);}

    public void saveProduct(Product product){
        productRepository.save(product);
    }


}
