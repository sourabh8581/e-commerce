package com.ecommerce.product.product;

import com.ecommerce.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public Integer createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductpurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        List<Integer> purchasedProds = requestList.stream()
                .map(ProductPurchaseRequest::productId)
                .collect(Collectors.toList());

        List<Product> storedProds = productRepository.findAllByIdInOrderById(purchasedProds);
        if(purchasedProds.size() != storedProds.size()){
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<ProductPurchaseRequest> storedRequests = requestList.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        List<ProductpurchaseResponse> purchasedProducts =new ArrayList<ProductpurchaseResponse>();
        for (int i=0; i<storedProds.size(); i++){
            Product product = storedProds.get(i);
            ProductPurchaseRequest productPurchaseRequest1 = storedRequests.get(i);
            if(product.getAvailableQuantity()<productPurchaseRequest1.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for product with id : "+product.getId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest1.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product,productPurchaseRequest1.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product not found with ID : "+ productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
