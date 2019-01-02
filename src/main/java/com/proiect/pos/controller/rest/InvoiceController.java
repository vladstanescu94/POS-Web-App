package com.proiect.pos.controller.rest;


import com.proiect.pos.model.Coupon;
import com.proiect.pos.model.Invoice;
import com.proiect.pos.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InvoiceController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/coupon/check",method = RequestMethod.GET)
    public int checkCoupon(String code){
        int discountPercentage=couponService.getDiscount(code);
        return discountPercentage;
    }

    @RequestMapping(value="/coupon/apply",method = RequestMethod.GET)
    public int applyCoupon(String code, HttpServletRequest request){
        Coupon coupon = couponService.findByCode(code);
        if(couponService.isCouponValid(coupon))
        {
            Invoice invoice=(Invoice)request.getSession().getAttribute("invoice");
            invoice.setCoupon(coupon);
            request.getSession().setAttribute("invoice",invoice);
            return coupon.getDiscountPercentage();
        }
        return -1;
    }

}
