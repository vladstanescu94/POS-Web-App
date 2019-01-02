package com.proiect.pos.controller;

import com.proiect.pos.model.*;
import com.proiect.pos.service.InvoiceService;
import com.proiect.pos.service.ProductService;
import com.proiect.pos.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private InvoiceService invoiceService;


    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public ModelAndView getSaleWebPage(HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        ModelAndView modelAndView = new ModelAndView();
        if (invoice == null) {
            request.getSession().setAttribute("invoice", new Invoice());
        } else {
            modelAndView.addObject("value", invoice);
            List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
            modelAndView.addObject("invoiceItems", invoiceItems);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/allProducts", method = RequestMethod.GET)
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView();
        Product[] products = productService.findAllByStockGreaterThanEqual(1);
        modelAndView.addObject("products", products);
//        modelAndView.setViewName("allProducts");
        return modelAndView;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView proceedToCheckout(HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        if (invoice == null) {
            return new ModelAndView("redirect:/sale");
        }
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Seller seller = sellerService.findByUsername(user.getUsername());
        invoice.setId(System.currentTimeMillis() / 1000);
        invoice.setPurchaseDate(new Date());
        invoice.setSeller(seller);
        BigDecimal initialPrice = BigDecimal.ZERO;
        List<InvoiceItem>invoiceItems=invoice.getInvoiceItems();
        for(InvoiceItem item: invoiceItems)
        {
            BigDecimal qty=new BigDecimal(item.getQuantity());
            BigDecimal price=item.getProduct().getPrice();
            initialPrice=initialPrice.add(price.multiply(qty));
        }

        BigDecimal discountedPrice = initialPrice;
        invoice.setDiscountedPrice(discountedPrice);
        invoice.setInitialPrice(initialPrice);


        modelAndView.addObject("invoice", invoice);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("coupon", new Coupon());
        modelAndView.addObject("invoiceItems", invoiceItems);

        return modelAndView;
    }

    @RequestMapping(value = "/needBetterUrlForThis",method = RequestMethod.GET)
    public String finalizeTransaction(HttpServletRequest request)
    {
        Invoice invoice=(Invoice)request.getSession().getAttribute("invoice");
        invoiceService.saveInvoice(invoice);
        return "home";
    }

}
