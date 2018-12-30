package com.proiect.pos.controller.rest;

import com.proiect.pos.model.Invoice;
import com.proiect.pos.model.InvoiceItem;
import com.proiect.pos.model.Product;
import com.proiect.pos.repository.ProductRepository;
import com.proiect.pos.repository.SellerRepository;
import com.proiect.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ProductController {


    @Autowired
    ProductService productService;

    @Autowired
    SellerRepository sellerRepository;


    //TODO RENAME INTO BETTER URL
    @RequestMapping(path = "/getProductID", method = RequestMethod.GET)
    public Product getProductId(int id, HttpServletRequest request) {
        System.out.println("COD VALUE IS:" + id);
        Product productExists = productService.findById(id);
        Product product = null;
        if (productExists != null) {
            product = productExists;
            updateInvoiceQuantityOfProduct(id, 1, request);
        }
        return product;
    }


    @RequestMapping(path="/removeProduct",method=RequestMethod.GET)
    public String removeProductFromInvoice(int id,HttpServletRequest request)
    {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        Map<Integer, Integer> products = invoice.getProducts();
        if(products.containsKey(id))
        {
            products.remove(id);
            request.getSession().setAttribute("invoice", invoice);
            return "removeSuccess";
        }
        return "noProduct";
    }

    @RequestMapping(path="/removeAllProducts",method=RequestMethod.POST)
    public void removeAllProductsFromInvoice(HttpServletRequest request)
    {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        Map<Integer, Integer> products = invoice.getProducts();
        products.clear();
        request.getSession().setAttribute("invoice", invoice);
    }

    @RequestMapping(path = "/increaseQuantity", method = RequestMethod.GET)
    public String increaseProductQuantity(int id, HttpServletRequest request) {

        if (hasProductStock(id, 0, request)) {
            updateInvoiceQuantityOfProduct(id, 1, request);
            return "increaseSuccess";
        }
        return "EOS";
    }

    @RequestMapping(path = "/decreaseQuantity", method = RequestMethod.GET)
    public String decreaseProductQuantity(int id, HttpServletRequest request) {
        updateInvoiceQuantityOfProduct(id, -1, request);
        return "decreaseSuccess";
    }

    @RequestMapping(path = "/customQuantity", method = RequestMethod.GET)
    public String setCustomQuantity(int id, int quantity, HttpServletRequest request) {
        if (hasProductStock(id, quantity, request)) {
            updateInvoiceQuantityOfProduct(id,quantity,request);
            return "customSuccess";
        }
        return "EOS";
    }


    private void updateInvoiceQuantityOfProduct(int id, int ammount, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        Map<Integer, Integer> products = invoice.getProducts();
        int count = products.containsKey(id) ? products.get(id) : 0;
        products.put(id, count + ammount);
        request.getSession().setAttribute("invoice", invoice);
    }

    private boolean hasProductStock(int id, int minValue, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        int productSelectedQuantity = invoice.getProducts().get(id);
        int productStock = productService.findById(id).getStock();
        if (productStock - productSelectedQuantity > minValue)
            return true;
        return false;
    }
}
