package com.proiect.pos.controller.rest;


import com.proiect.pos.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/coupon")
public class InvoiceController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/use",method = RequestMethod.GET)
    public int useCoupon(String code){
        int discountPercentage=couponService.getDiscount(code);
        //TODO interract with Invoice
        return discountPercentage;
    }

}
