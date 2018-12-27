package com.proiect.pos.controller;

import com.proiect.pos.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.proiect.pos.service.SellerService;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private SellerService sellerService;


    @RequestMapping("/")
    String home() {
        return "login";
    }
    @RequestMapping("/home")
    String index() {
        return "home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = new Seller();
        modelAndView.addObject("seller", seller);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewSeller(@Valid Seller seller, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Seller sellerExists = sellerService.findByUsername(seller.getUsername());
        if (sellerExists != null) {
            bindingResult.rejectValue("username", "error.seller",
                    "There is already a seller with this username");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
            sellerService.saveSeller(seller);
            modelAndView.addObject("successMessage","User has been registered successfully");
            modelAndView.addObject("seller",new Seller());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/test",method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller= sellerService.findByUsername(auth.getName());
        modelAndView.addObject("userName", "Welcome " + seller.getFirstName() + " " + seller.getLastName() + " (" + seller.getUsername() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("/admin/test");
        return modelAndView;
    }

}
