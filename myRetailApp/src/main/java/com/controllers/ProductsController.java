package com.controllers;

import com.dao.ProductsDao;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class ProductsController {

    @Autowired
    private ProductsDao productsDao;

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public String getProductDetailsById(@PathVariable("productId") String productId) {
        List<Product> list = productsDao.getProductDetailsById(productId);
        if (!list.isEmpty()) return list.toString();
        return "No Matched Records Found";
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT)
    public void upsertProductDetailsById(@PathVariable("productId") String productId, @RequestBody Map productDetails) {
        productsDao.upsertProductDetails(productDetails,productId);
    }

}
