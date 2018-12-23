package com.proiect.pos.service;

import com.proiect.pos.model.Role;
import com.proiect.pos.model.Seller;
import com.proiect.pos.repository.RoleRepository;
import com.proiect.pos.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("sellerService")
public class SellerService {
    private SellerRepository sellerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sellerRepository = sellerRepository;
        this.roleRepository=roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Seller findByUsername(String username) {
        return sellerRepository.findByUsername(username);
    }

    public void saveSeller(Seller seller) {
        seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        seller.setActive(1);
        seller.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        sellerRepository.save(seller);
    }
}
