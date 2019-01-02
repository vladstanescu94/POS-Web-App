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

    public Product[] findAllByStockGreaterThanEqual(int amount){return productRepository.findAllByStockGreaterThanEqual(amount);}

    public void saveProduct(Product product){
        String path= "/media/" +product.getImage()+".png";
        product.setImage(path);
        productRepository.save(product);
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }


}
