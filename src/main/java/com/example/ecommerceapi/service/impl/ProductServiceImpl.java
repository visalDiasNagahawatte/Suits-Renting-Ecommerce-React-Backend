package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.ProductDTO;
import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.exception.EntryDuplicateException;
import com.example.ecommerceapi.exception.EntryNotFoundException;
import com.example.ecommerceapi.exception.NotFoundException;
import com.example.ecommerceapi.repo.ProductRepo;
import com.example.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addProduct(ProductDTO productDTO) {
        if(!Objects.isNull(productDTO)){
            if(!repo.existsByTitle(productDTO.getTitle())){
                repo.save(mapper.map(productDTO,Product.class));
                return true;
            }else{
                throw new EntryDuplicateException("Already exists...");
            }
        }else{
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO, String title) {
        if(!Objects.isNull(productDTO)){
            Optional<Product> byTitle = repo.findByTitle(title);
            if(byTitle.isPresent()){
                Product product = byTitle.get();
                product.setCategoryId(byTitle.get().getCategoryId());
                product.setDescription(productDTO.getDescription());
                product.setTitle(productDTO.getTitle());
                product.setPrice(productDTO.getPrice());
                product.setStockQty(productDTO.getStockQty());
                product.setImgUrl(productDTO.getImgUrl());
                product.setInStock(productDTO.isInStock());
                repo.save(product);
                return true;
            }else{
                throw new NotFoundException("Not found...");
            }
        }else{
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public String deleteProduct(String title) {
        if(!title.isEmpty()){
            if(repo.existsByTitle(title)){
                return repo.deleteByTitle(title);
            }else{
                throw new RuntimeException("Not found...");
            }
        }else{
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public ProductDTO findProduct(String title) {
        if(!title.isEmpty()){
            Optional<Product> productOptional = repo.findByTitle(title);
            if(productOptional.isPresent()){
                return mapper.map(productOptional.get(), ProductDTO.class);
            }else{
                throw new NotFoundException("Not found...");
            }
        }else{
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<ProductDTO>>(){}.getType());
    }
}
