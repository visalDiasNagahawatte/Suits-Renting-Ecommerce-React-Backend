package com.example.ecommerceapi.service;

import com.example.ecommerceapi.dto.CategoryDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 24-Jul-23
 */
public interface CategoryService {
    boolean addCategory(CategoryDTO categoryDTO);
    boolean updateCategory(CategoryDTO categoryDTO, String description);
    String deleteCategory(String description);
    CategoryDTO findCategory(String description);
    List<CategoryDTO> getAllCategory();
}
