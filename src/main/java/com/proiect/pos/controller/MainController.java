package com.proiect.pos.controller;

import com.proiect.pos.model.Coupon;
import com.proiect.pos.model.Invoice;
import com.proiect.pos.model.Product;
import com.proiect.pos.model.Seller;
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


    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public ModelAndView getSaleWebPage(HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        ModelAndView modelAndView = new ModelAndView();
        if (invoice == null) {
            request.getSession().setAttribute("invoice", new Invoice());
        } else {
            modelAndView.addObject("value", invoice);
            List<Product> products = new ArrayList<>();
            Map<Integer, Integer> productsMap = invoice.getShoppingCart();

            for (Map.Entry<Integer, Integer> entry : productsMap.entrySet()) {
                products.add(productService.findById(entry.getKey()));
            }
            modelAndView.addObject("products", products);
            modelAndView.addObject("map", productsMap);
        }
//            modelAndView.setViewName("sale");
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
        modelAndView.addObject("quantity", invoice.getShoppingCart());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Seller seller = sellerService.findByUsername(user.getUsername());
        invoice.setId(System.currentTimeMillis() / 1000);
        invoice.setPurchaseDate(new Date());
        invoice.setSellerId(seller.getId());

        List<Product> products = new ArrayList<>();
        Map<Integer, Integer> productsMap = invoice.getShoppingCart();
        BigDecimal initialPrice = BigDecimal.ZERO;
        for (Map.Entry<Integer, Integer> entry : productsMap.entrySet()) {
            Product product = productService.findById(entry.getKey());
            BigDecimal qty = new BigDecimal(entry.getValue());
            BigDecimal price = product.getPrice();
            initialPrice = initialPrice.add(price.multiply(qty));
            products.add(product);
        }
        BigDecimal discountedPrice = initialPrice;
        invoice.setDiscountedPrice(discountedPrice);
        invoice.setInitialPrice(initialPrice);


        modelAndView.addObject("invoice", invoice);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("coupon", new Coupon());
        modelAndView.addObject("products", products);

        return modelAndView;
    }

}
