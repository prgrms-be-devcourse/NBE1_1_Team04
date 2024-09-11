package com.grepp.nbe1_1_clone_mw1.product.controller;


import com.grepp.nbe1_1_clone_mw1.product.controller.dto.CreateProductRequest;
import com.grepp.nbe1_1_clone_mw1.product.controller.dto.UpdateProductRequest;
import com.grepp.nbe1_1_clone_mw1.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public String productsPage(Model model) {
    var products = productService.getAllProducts();
    model.addAttribute("products", products);
    return "product-list";
  }

  @GetMapping("new-product")
  public String newProductPage() {
    return "new-product";
  }

  @PostMapping("/products")
  public String newProduct(CreateProductRequest createProductRequest) {
    productService.createProduct(
      createProductRequest.productName(),
      createProductRequest.category(),
      createProductRequest.price(),
      createProductRequest.description());
    return "redirect:/products";
  }

  @GetMapping("update-product")
  public String updateProductPage(@RequestParam("productId") String productId, Model model) {
    model.addAttribute("productId", productId);
    return "update-product";
  }

  @PostMapping("/products/update")
  public String updateProduct(@RequestParam("productId") String productId, UpdateProductRequest updateProductRequest) {
    productService.updateProduct(productId, updateProductRequest);
    return "redirect:/products";
  }

}
