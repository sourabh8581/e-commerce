package com.ecommerce.product.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductpurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requestList
    ){
        return ResponseEntity.ok(service.purchaseProducts(requestList));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProduct(
            @PathVariable("product-id") Integer productId ){
        return ResponseEntity.ok(service.findById(productId));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
