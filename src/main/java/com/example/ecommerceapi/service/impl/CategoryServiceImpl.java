package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.dto.CategoryDTO;
import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.exception.EntryDuplicateException;
import com.example.ecommerceapi.exception.EntryNotFoundException;
import com.example.ecommerceapi.exception.NotFoundException;
import com.example.ecommerceapi.repo.CategoryRepo;
import com.example.ecommerceapi.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addCategory(CategoryDTO categoryDTO) {
        if(!Objects.isNull(categoryDTO)){
            if(!repo.existsByDescription(categoryDTO.getDescription())){
                repo.save(mapper.map(categoryDTO, Category.class));
                return true;
            }else{
                throw new EntryDuplicateException("Already exists...");
            }
        }else {
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO, String description) {
        if(!Objects.isNull(categoryDTO)){
            Optional<Category> byDescription = repo.findByDescription(description);
            if(byDescription.isPresent()){
                Category category = byDescription.get();
                category.setDescription(categoryDTO.getDescription());
                repo.save(category);
                return true;
            }else{
                throw new NotFoundException("Not found...");
            }
        }else{
            throw new IllegalArgumentException("Invalid inputs...");
        }
    }

    @Override
    public String deleteCategory(String description) {
        if(!description.isEmpty()){
            if(repo.existsByDescription(description)){
                return repo.deleteByDescription(description);
            }else{
                throw new NotFoundException("Not found...");
            }
        }else{
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public CategoryDTO findCategory(String description) {
        if(!description.isEmpty()){
            Optional<Category> categoryOptional = repo.findByDescription(description);
            if(categoryOptional.isPresent()){
                return mapper.map(categoryOptional.get(), CategoryDTO.class);
            }else{
                throw new NotFoundException("Not found...");
            }
        }else{
            throw new EntryNotFoundException("Invalid inputs...");
        }
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> all = repo.findAll();
        return mapper.map(all,new TypeToken<List<CategoryDTO>>(){}.getType());
    }
}
