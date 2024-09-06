package org.grepp.nbe1_1_team04.product.controller;

import org.grepp.nbe1_1_team04.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


}
