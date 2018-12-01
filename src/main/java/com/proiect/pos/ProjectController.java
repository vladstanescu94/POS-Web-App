package com.proiect.pos;

import com.proiect.pos.Entities.Product;
import com.proiect.pos.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProjectController {


    @Autowired
    ProductRepository productRepository;

    @RequestMapping(path="/getProductID",method = RequestMethod.GET)
    public Product getProductId(Long id)
    {
        System.out.println("COD VALUE IS:"+id);
        Optional<Product> productOptional=productRepository.findById(id);
        Product product=null;
        if(productOptional.isPresent()){
            product=productOptional.get();
        }
        return product;
    }
}
