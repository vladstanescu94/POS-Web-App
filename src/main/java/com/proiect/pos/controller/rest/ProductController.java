package com.proiect.pos.controller.rest;

import com.proiect.pos.model.Invoice;
import com.proiect.pos.model.InvoiceItem;
import com.proiect.pos.model.Product;
import com.proiect.pos.repository.SellerRepository;
import com.proiect.pos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
        Product product = productService.findById(id);

        if (product != null) {
            Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
            List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
            boolean itemNotFound = true;
            for (InvoiceItem item : invoiceItems) {
                int itemId = item.getProduct().getId();
                if (itemId == id) {
                    int itemQty = item.getQuantity();
                    item.setQuantity(itemQty + 1);
                    itemNotFound = false;
                }
            }
            if (itemNotFound) {
                InvoiceItem item = new InvoiceItem();
                item.setQuantity(1);
                item.setProduct(product);
                item.setInvoice(invoice);
                invoiceItems.add(item);
            }
            request.getSession().setAttribute("invoice", invoice);
        }

        return product;
    }


    @RequestMapping(path = "/removeProduct", method = RequestMethod.GET)
    public String removeProductFromInvoice(int id, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        for (InvoiceItem item : invoiceItems) {
            Product product=item.getProduct();
            int productId = product.getId();
            if (id == productId) {
                invoiceItems.remove(item);
                request.getSession().setAttribute("invoice", invoice);
                return "removeSuccess";
            }
        }
        return "ERROR";
    }

    @RequestMapping(path = "/removeAllProducts", method = RequestMethod.POST)
    public void removeAllProductsFromInvoice(HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        invoice.getInvoiceItems().clear();
        request.getSession().setAttribute("invoice", invoice);
    }

    @RequestMapping(path = "/increaseQuantity", method = RequestMethod.GET)
    public String increaseProductQuantity(int id, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        for (InvoiceItem item : invoiceItems) {
            Product product = item.getProduct();
            int productId = product.getId();
            if (id == productId) {
                int stock = product.getStock();
                int alreadySelectedQty = item.getQuantity();
                if (stock > alreadySelectedQty) {
                    alreadySelectedQty++;
                    item.setQuantity(alreadySelectedQty);
                    request.getSession().setAttribute("invoice", invoice);
                    return "increaseSuccess";
                }
            }
        }
        return "EOS";
    }

    @RequestMapping(path = "/decreaseQuantity", method = RequestMethod.GET)
    public String decreaseProductQuantity(int id, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        for (InvoiceItem item : invoiceItems) {
            Product product = item.getProduct();
            int productId = product.getId();
            if (id == productId) {
                int alreadySelectedQty = item.getQuantity();
                alreadySelectedQty--;
                item.setQuantity(alreadySelectedQty);
                request.getSession().setAttribute("invoice", invoice);
            }
        }
        return "decreaseSuccess";
    }


    //TODO IMPLEMENT COUNTERPART ON FRONT_END
    @RequestMapping(path = "/customQuantity", method = RequestMethod.GET)
    public String setCustomQuantity(int id, int desiredQty, HttpServletRequest request) {
        Invoice invoice = (Invoice) request.getSession().getAttribute("invoice");
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItems();
        for (InvoiceItem item : invoiceItems) {
            Product product = item.getProduct();
            Integer productId = product.getId();
            if (id == productId) {
                int stock = product.getStock();
                int alreadySelectedQty = item.getQuantity();
                if (stock >= alreadySelectedQty + desiredQty) {
                    alreadySelectedQty += desiredQty;
                    item.setQuantity(alreadySelectedQty);
                }
                request.getSession().setAttribute("invoice", invoice);
                return "customSuccess";
            }
        }
        return "EOS";
    }


}
