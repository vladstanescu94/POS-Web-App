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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "addNewProduct", method = RequestMethod.POST)
    public ModelAndView addNewProduct(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
        Product productExists = productService.findById(product.getId());
        if (productExists != null)
        {
            bindingResult.rejectValue("id", "error.product", "There's already a product with this barcode");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            redirectAttributes.addFlashAttribute("product", product);
        }

        if (!bindingResult.hasErrors())
        {
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product has been added successfully");
            redirectAttributes.addFlashAttribute("product", new Product());

        }
        return modelAndView;
    }

}
