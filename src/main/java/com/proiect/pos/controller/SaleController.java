package com.proiect.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SaleController {
    @RequestMapping(value = "/sale",method = RequestMethod.GET)
    public String getSaleWebPage()
    {

        return "sale";
    }
}
