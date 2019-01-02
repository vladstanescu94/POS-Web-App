package com.proiect.pos.controller;

import com.proiect.pos.model.*;
import com.proiect.pos.service.CouponService;
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
            List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
            Coupon coupon=invoice.getCoupon();
            coupon=(coupon!=null)? coupon:new Coupon();
            modelAndView.addObject("invoiceItems", invoiceItems);
            modelAndView.addObject("discountPercentage",coupon.getDiscountPercentage());
        }
        return modelAndView;
    }


    @RequestMapping(value = "/allProducts", method = RequestMethod.GET)
    public ModelAndView showAllProducts() {
        ModelAndView modelAndView = new ModelAndView();
        Product[] products = productService.findAllByStockGreaterThanEqual(1);
        modelAndView.addObject("products", products);
        return modelAndView;
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView proceedToCheckout(HttpServletRequest request) {

        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        if (invoice == null) {
            return new ModelAndView("redirect:/sale");
        }

        ModelAndView modelAndView = new ModelAndView();

        invoice.setId(System.currentTimeMillis() / 1000);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Seller seller = sellerService.findByUsername(user.getUsername());
        invoice.setSeller(seller);

        List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        Coupon coupon=invoice.getCoupon();
        coupon=(coupon!=null)? coupon:new Coupon();

        BigDecimal initialPrice = computeInitialPrice(invoiceItems);
        BigDecimal discountedPrice = computeDiscountedPrice(initialPrice,coupon);

        invoice.setInitialPrice(initialPrice);
        invoice.setDiscountedPrice(discountedPrice);

        request.getSession().setAttribute("invoice",invoice);

        modelAndView.addObject("invoice", invoice);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("coupon", coupon);
        modelAndView.addObject("invoiceItems", invoiceItems);

        return modelAndView;
    }


    private BigDecimal computeDiscountedPrice(BigDecimal initialPrice, Coupon coupon) {

        BigDecimal discountedPrice = initialPrice;
        int discountPercentage = coupon.getDiscountPercentage();
        double computedPercentage = (100 - discountPercentage) / 100d;
        BigDecimal discount = new BigDecimal(computedPercentage);
        discountedPrice = discountedPrice.multiply(discount);
        return discountedPrice;
    }


    private BigDecimal computeInitialPrice(List<InvoiceItem> invoiceItems) {
        BigDecimal initialPrice = BigDecimal.ZERO;
        for (InvoiceItem item : invoiceItems) {
            BigDecimal qty = new BigDecimal(item.getQuantity());
            BigDecimal price = item.getProduct().getPrice();
            initialPrice = initialPrice.add(price.multiply(qty));
        }
        return initialPrice;
    }


    @RequestMapping(value = "/needBetterUrlForThis", method = RequestMethod.GET)
    public String finalizeTransaction(HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        invoiceService.saveInvoice(invoice);
        updateProductsStock(invoice.getInvoiceItems());
        request.getSession().setAttribute("invoice",new Invoice());
        return "home";
    }

    private void updateProductsStock(List<InvoiceItem> invoiceItems) {
        for (InvoiceItem item:invoiceItems) {
            Product product=item.getProduct();
            int stock=product.getStock();
            int boughtQty=item.getQuantity();
            int newStock=stock-boughtQty;
            product.setStock(newStock);
            productService.updateProduct(product);
        }
    }

}
