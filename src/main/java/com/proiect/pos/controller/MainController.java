package com.proiect.pos.controller;

import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import com.proiect.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;

@Controller
public class MainController {


    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String getSaleWebPage() {
        return "sale";
    }


    @RequestMapping(value="/allProducts",method=RequestMethod.GET)
    public ModelAndView showAllProducts(){
        ModelAndView modelAndView=new ModelAndView();
        Product[] products=productService.findAllByStockGreaterThanEqual(1);
        modelAndView.addObject("products",products);
//        modelAndView.setViewName("allProducts");
        return modelAndView;
    }

}
