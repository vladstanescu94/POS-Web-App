package com.proiect.pos.repository;

import com.proiect.pos.model.Coupon;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon,Integer> {
    Coupon findByCode(String code);
}
