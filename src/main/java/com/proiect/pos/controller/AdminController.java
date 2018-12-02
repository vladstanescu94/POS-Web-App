package com.proiect.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping(value = "addproduct",method = RequestMethod.GET)
    public ModelAndView getAddProductPage(){
        ModelAndView modelAndView=new ModelAndView();


        modelAndView.setViewName("admin/addproduct");
        return modelAndView;
    }
}
