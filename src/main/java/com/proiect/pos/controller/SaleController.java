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
public class SaleController {


    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String getSaleWebPage() {

        return "sale";
    }

}
