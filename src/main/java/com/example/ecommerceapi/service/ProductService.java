package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.ProductDTO;
import com.example.ecommerceapi.entity.Product;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface ProductService {
    boolean addProduct(ProductDTO product);
    boolean updateProduct(ProductDTO product, String title);
    String deleteProduct(String title);
    ProductDTO findProduct(String title);
    List<ProductDTO> getAllProducts();
}
