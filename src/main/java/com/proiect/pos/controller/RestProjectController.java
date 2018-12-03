package com.proiect.pos.controller;
import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import com.proiect.pos.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class RestProjectController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @RequestMapping(path="/getProductID",method = RequestMethod.GET)
    public Product getProductId(int id)
    {
        System.out.println("COD VALUE IS:"+id);
        Product productExists=productRepository.findById(id);
        Product product=null;
        if(productExists!=null){
            product=productExists;
        }
        return product;
    }


}
