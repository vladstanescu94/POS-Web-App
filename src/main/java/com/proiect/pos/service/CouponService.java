package com.proiect.pos.service;

import com.proiect.pos.model.Coupon;
import com.proiect.pos.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("couponService")
public class CouponService {

    private CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon findByCode(String code) {
        return couponRepository.findByCode(code);
    }


    public int getDiscount(String code) {
        Coupon coupon = findByCode(code);
        if (coupon != null) {
            if (isCouponValid(coupon)) {
                return coupon.getDiscountPercentage();
            }
        }
        return -1;
    }

    private boolean isCouponValid(Coupon coupon) {
        Date currentDate = new Date();
        if (currentDate.compareTo(coupon.getExpiryDate()) <= 0)
            return true;
        return false;
    }
}
