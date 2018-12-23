package com.proiect.pos.controller.rest;
import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import com.proiect.pos.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class ProductController {


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

    @RequestMapping(path="/increaseQuantity",method = RequestMethod.GET)
    public String increaseProductQuantity(int id){
        //TODO ADI: IMPLEMENT LOGIC BACKEND FOR INCREASING QUANTITY
        /*
        should return increaseSuccess if increasing was possible,
                        EOS if there's no more stock for it,
                        error if there's any other type of error
         */
        return "increaseSuccess";
    }

    @RequestMapping(path="/decreaseQuantity",method=RequestMethod.GET)
    public String decreaseProductQuantity(int id){
        //TODO ADI: IMPLEMENT LOGIC BACKEND FOR INCREASING QUANTITY
        /*
        should return decreaseSuccess if increasing was possible,
                        error if there's any other type of error
        */
        return "decreaseSuccess";
    }

    @RequestMapping(path="/customQuantity",method=RequestMethod.GET)
    public String setCustomQuantity(int id, int quantity){
        //TODO ADI: IMPLEMENT LOGIC BACKEND FOR SETTING CUSTOM QUANTITY
         /*
        should return increaseSuccess if increasing was possible,
                        EOS if there's no more stock for it,
                        error if there's any other type of error
         */
        return "customSucces";
    }


}
