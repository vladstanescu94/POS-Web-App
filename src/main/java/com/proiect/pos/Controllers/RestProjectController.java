//package com.proiect.pos.Controllers;
//
//import com.proiect.pos.OLD.Product;
//import com.proiect.pos.repository.ProductRepository;
//import com.proiect.pos.repository.SellerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//public class RestProjectController {
//
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    SellerRepository sellerRepository;
//
//    @RequestMapping(path="/getProductID",method = RequestMethod.GET)
//    public Product getProductId(Long id)
//    {
//        System.out.println("COD VALUE IS:"+id);
//        Optional<Product> productOptional=productRepository.findById(id);
//        Product product=null;
//        if(productOptional.isPresent()){
//            product=productOptional.get();
//        }
//        return product;
//    }
//
//
//}
