package com.proiect.pos.controller;

import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import com.proiect.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "addproduct", method = RequestMethod.GET)
    public ModelAndView getAddProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("admin/addproduct");
        return modelAndView;
    }

    @RequestMapping(value = "addproduct", method = RequestMethod.POST)
    public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Product productExists = productService.findById(product.getId());
        if (productExists != null) {
            bindingResult.rejectValue("id", "error.product",
                    "There's already a product with this barcode");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/addproduct");
        } else {
            productService.saveProduct(product);
            modelAndView.addObject("successMessage", "Product has been added successfully");
            modelAndView.addObject("product", new Product());
            modelAndView.setViewName("admin/addproduct");
        }
        return modelAndView;
    }
}
