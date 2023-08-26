package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.ProductDTO;
import com.example.ecommerceapi.service.ProductService;
import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 23-Jul-23
 */
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<StandardResponse> addProduct(@RequestBody ProductDTO productDTO) {
        boolean b = service.addProduct(productDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success", b), HttpStatus.CREATED);
    }

    @PutMapping(params = {"title"})
    public ResponseEntity<StandardResponse> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam String title) {
        boolean b = service.updateProduct(productDTO, title);
        return new ResponseEntity<>(new StandardResponse(204, "Success", b), HttpStatus.OK);
    }

    @DeleteMapping(params = {"title"})
    public ResponseEntity<StandardResponse> deleteProduct(@RequestParam String title) {
        String s = service.deleteProduct(title);
        return new ResponseEntity<>(new StandardResponse(204, "Success", s), HttpStatus.OK);
    }

    @GetMapping(value = "/find", params = {"title"})
    public ResponseEntity<StandardResponse> findProduct(@RequestParam String title) {
        ProductDTO product = service.findProduct(title);
        return new ResponseEntity<>(new StandardResponse(200, "Success", product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllProducts(){
        List<ProductDTO> allProducts = service.getAllProducts();
        return new ResponseEntity<>(new StandardResponse(200,"Success", allProducts), HttpStatus.OK);
    }
}
